package com.example.PrimerParcial.service;

import com.example.PrimerParcial.entities.DNA;
import com.example.PrimerParcial.repository.DNARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DNAServiceImplTest {

    @Mock
    private DNARepository dnaRepository;

    @InjectMocks
    private DNAServiceImpl dnaServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testIsMutant() throws Exception {
        //ADN de mutante
        String[] mutantDNA = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        String dnaString = String.join(",", mutantDNA);
        DNA dna = DNA.builder().dna(dnaString).build();

        //Simulamos que el ADN no está en la base de datos
        when(dnaRepository.findByDna(dnaString)).thenReturn(Optional.empty());

        //Verificamos que el servicio detecta como mutante
        boolean isMutant = dnaServiceImpl.isMutant(dna);
        assertTrue(isMutant);

        //Verificamos que el ADN fue guardado en la base de datos
        verify(dnaRepository, times(1)).save(any(DNA.class));
    }


    @Test
    public void testIsMutantDatabaseError() throws Exception {
        String dnaString = "ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG";
        DNA dna = DNA.builder().dna(dnaString).build();

        //Simulamos una excepción en la base de datos
        when(dnaRepository.findByDna(dnaString)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> {
            dnaServiceImpl.isMutant(dna);
        });

        assertEquals("Database error", exception.getMessage());
    }

    @Test
    public void testIsMutantWithTwoSequences() throws Exception {
        //ADN de mutante con dos secuencias de 4 letras consecutivas
        String[] mutantDNA = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCCTA", "TCACTG"};
        String dnaString = String.join(",", mutantDNA);
        DNA dna = DNA.builder().dna(dnaString).build();

        //Simulamos que el ADN no está en la base de datos
        when(dnaRepository.findByDna(dnaString)).thenReturn(Optional.empty());

        //Verificamos que el servicio detecta como mutante
        boolean isMutant = dnaServiceImpl.isMutant(dna);
        assertTrue(isMutant);

        //Verificamos que el ADN fue guardado en la base de datos
        verify(dnaRepository, times(1)).save(any(DNA.class));
    }

    @Test
    public void testCheckDiagonalsMutantFound() {
        //Simulamos una matriz de ADN con una secuencia mutante en diagonal izquierda a derecha
        char[][] matrix = {
                {'A', 'T', 'G', 'C', 'A', 'G'},
                {'C', 'A', 'G', 'T', 'G', 'C'},
                {'T', 'T', 'A', 'T', 'A', 'T'},
                {'A', 'G', 'T', 'A', 'G', 'G'},
                {'C', 'C', 'T', 'C', 'T', 'A'},
                {'T', 'C', 'A', 'C', 'T', 'G'}
        };

        //Verificamos que el metodo detecta una secuencia mutante en diagonal
        int mutantSequences = dnaServiceImpl.checkDiagonals(matrix);
        assertEquals(2, mutantSequences);
    }

    @Test
    public void testCheckDiagonalsNoMutant() {
        //Simulamos una matriz de ADN que no tiene secuencias mutantes en las diagonales
        char[][] matrix = {
                {'A', 'T', 'G', 'C', 'A', 'G'},
                {'C', 'T', 'G', 'T', 'G', 'C'},
                {'T', 'T', 'A', 'C', 'A', 'T'},
                {'A', 'G', 'A', 'G', 'T', 'G'},
                {'C', 'A', 'C', 'C', 'T', 'A'},
                {'T', 'C', 'A', 'C', 'A', 'G'}
        };

        //Verificamos que no se detectan secuencias mutantes
        int mutantSequences = dnaServiceImpl.checkDiagonals(matrix);
        assertEquals(0, mutantSequences); //No debe haber ninguna secuencia mutante
    }
}
