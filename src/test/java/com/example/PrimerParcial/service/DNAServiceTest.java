package com.example.PrimerParcial.service;

import com.example.PrimerParcial.entities.DNA;
import com.example.PrimerParcial.repository.DNARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DNAServiceTest {

    @Mock
    private DNARepository dnaRepository;

    @InjectMocks
    private DNAServiceImpl dnaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsMutant() throws Exception {
        // ADN de mutante
        String[] mutantDNA = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        String dnaString = String.join(",", mutantDNA);
        DNA dna = DNA.builder().dna(dnaString).build();

        // Simulamos que el ADN no está en la base de datos
        when(dnaRepository.findByDna(dnaString)).thenReturn(Optional.empty());

        // Verificamos que el servicio detecta como mutante
        boolean isMutant = dnaService.isMutant(dna);
        assertTrue(isMutant);

        // Verificamos que el ADN fue guardado en la base de datos
        verify(dnaRepository, times(1)).save(any(DNA.class));
    }

    @Test
    public void testIsNotMutant() throws Exception {
        // ADN de humano
        String[] humanDNA = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"};
        String dnaString = String.join(",", humanDNA);
        DNA dna = DNA.builder().dna(dnaString).build();

        // Simulamos que el ADN no está en la base de datos
        when(dnaRepository.findByDna(dnaString)).thenReturn(Optional.empty());

        // Verificamos que el servicio detecta como humano
        boolean isMutant = dnaService.isMutant(dna);
        assertFalse(isMutant);

        // Verificamos que el ADN fue guardado en la base de datos
        verify(dnaRepository, times(1)).save(any(DNA.class));
    }

    @Test
    public void testIsMutantDatabaseError() throws Exception {
        String dnaString = "ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG";
        DNA dna = DNA.builder().dna(dnaString).build();

        // Simulamos una excepción en la base de datos
        when(dnaRepository.findByDna(dnaString)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> {
            dnaService.isMutant(dna);
        });

        assertEquals("Database error", exception.getMessage());
    }
}
