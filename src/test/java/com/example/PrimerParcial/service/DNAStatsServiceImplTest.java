package com.example.PrimerParcial.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.PrimerParcial.entities.DNAStats;
import com.example.PrimerParcial.repository.DNARepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DNAStatsServiceImplTest {


    @Mock
    private DNARepository dnaRepository;

    @InjectMocks
    private DNAStatsServiceImpl dnaStatsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalcularStats_ConDatos() throws Exception {
        //Arrange
        when(dnaRepository.countByIsMutant(true)).thenReturn(10L);
        when(dnaRepository.countByIsMutant(false)).thenReturn(5L);

        // Act
        DNAStats stats = dnaStatsService.calcularStats();

        //Assert
        assertEquals(10L, stats.getMutantCount());
        assertEquals(5L, stats.getNonMutantCount());
        assertEquals(2.0, stats.getMutantRatio(), 0.001);
    }

    @Test
    public void testCalcularStats_SoloHumanos() throws Exception {
        //Arrange
        when(dnaRepository.countByIsMutant(true)).thenReturn(0L);
        when(dnaRepository.countByIsMutant(false)).thenReturn(5L);

        //Act
        DNAStats stats = dnaStatsService.calcularStats();

        //Assert
        assertEquals(0L, stats.getMutantCount());
        assertEquals(5L, stats.getNonMutantCount());
        assertEquals(0.0, stats.getMutantRatio(), 0.001);
    }

    @Test
    public void testCalcularStats_SinDatos() throws Exception {
        //Arrange
        when(dnaRepository.countByIsMutant(true)).thenReturn(0L);
        when(dnaRepository.countByIsMutant(false)).thenReturn(0L);

        //Act
        DNAStats stats = dnaStatsService.calcularStats();

        //Assert
        assertEquals(0L, stats.getMutantCount());
        assertEquals(0L, stats.getNonMutantCount());
        assertEquals(0.0, stats.getMutantRatio(), 0.001);
    }
}