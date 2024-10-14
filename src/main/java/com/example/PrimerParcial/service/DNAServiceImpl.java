package com.example.PrimerParcial.service;

import com.example.PrimerParcial.entities.DNA;
import com.example.PrimerParcial.repository.BaseRepository;
import com.example.PrimerParcial.repository.DNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DNAServiceImpl extends BaseServiceImpl<DNA, Long> implements DNAService {
    @Autowired
    private DNARepository dnaRepository;

    public DNAServiceImpl(BaseRepository<DNA, Long> baseRepository){
        super(baseRepository);
    }

    @Override
    public boolean isMutant(DNA dna) {
        //Convierte la secuencia almacenada en la base de datos a String[]
        String dnaSequence = dna.getDna();
        String[] dnaArray = dnaSequence.split(",");

        //Verifica si ya existe en la base de datos
        Optional<DNA> existingDna = dnaRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()) {
            return existingDna.get().isMutant();
        }

        //Lógica para detección de mutante
        boolean mutantDetected = detectMutant(dnaArray);

        //Guarda en la base de datos
        dnaRepository.save(DNA.builder().dna(dnaSequence).isMutant(mutantDetected).build());

        return mutantDetected;
    }

    private boolean detectMutant(String[] dnaArray) {
        int n = dnaArray.length;
        int mutantSequences = 0;
        char[][] matrix = convertToMatrix(dnaArray);

        //Verificación de filas (horizontal)
        for (int i = 0; i < n; i++) {
            if (hasConsecutiveSequence(matrix[i])) {
                mutantSequences++;
                if (mutantSequences > 1) return true;
            }
        }

        //Verificación de columnas (vertical)
        for (int j = 0; j < n; j++) {
            if (hasConsecutiveSequence(getColumn(matrix, j))) {
                mutantSequences++;
                if (mutantSequences > 1) return true;
            }
        }

        //Verificación de diagonales
        mutantSequences += checkDiagonals(matrix);
        return mutantSequences > 1;
    }

    //Revisión de diagonales (ambos sentidos)
    private int checkDiagonals(char[][] matrix) {
        int n = matrix.length;
        int mutantSequences = 0;

        //Verificación de diagonales (izquierda a derecha)
        for (int i = 0; i < n - 3; i++) {
            if (hasConsecutiveSequence(getDiagonal(matrix, i, 0, true))) {
                mutantSequences++;
            }
            if (hasConsecutiveSequence(getDiagonal(matrix, 0, i, true))) {
                mutantSequences++;
            }
        }

        //Verificación de diagonales (derecha a izquierda)
        for (int i = 0; i < n - 3; i++) {
            if (hasConsecutiveSequence(getDiagonal(matrix, i, n - 1, false))) {
                mutantSequences++;
            }
            if (hasConsecutiveSequence(getDiagonal(matrix, 0, n - i - 1, false))) {
                mutantSequences++;
            }
        }
        return mutantSequences;
    }

    //Métodos auxiliares

    private char[][] convertToMatrix(String[] dna) {
        int n = dna.length;
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = dna[i].toCharArray();
        }
        return matrix;
    }

    private boolean hasConsecutiveSequence(char[] sequence) {
        int count = 1;
        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i] == sequence[i - 1]) {
                count++;
                if (count == 4) {
                    return true;  //Retorna true si se detecta una secuencia de 4 iguales
                }
            } else {
                count = 1;
            }
        }
        return false;  //Retorna false si no hay secuencia de 4
    }

    private char[] getColumn(char[][] matrix, int colIndex) {
        int n = matrix.length;
        char[] column = new char[n];
        for (int i = 0; i < n; i++) {
            column[i] = matrix[i][colIndex];
        }
        return column;
    }

    private char[] getDiagonal(char[][] matrix, int row, int col, boolean leftToRight) {
        int n = matrix.length;
        List<Character> diagonal = new ArrayList<>();
        while (row < n && col >= 0 && col < n) {
            diagonal.add(matrix[row][col]);
            row++;
            col += leftToRight ? 1 : -1;
        }

        char[] diagonalArray = new char[diagonal.size()];
        for (int i = 0; i < diagonal.size(); i++) {
            diagonalArray[i] = diagonal.get(i);
        }
        return diagonalArray;
    }

}
