package com.example.PrimerParcial.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.PrimerParcial.entities.DNAStats;
import com.example.PrimerParcial.service.DNAStatsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StatsController.class)
@ExtendWith(MockitoExtension.class)
class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DNAStatsServiceImpl dnaStatsService;

    @InjectMocks
    private StatsController statsController;

    @Test
    void shouldReturnStatsCorrectly() throws Exception {
        // Given
        DNAStats dnaStats = new DNAStats(40, 100, 0.4);
        when(dnaStatsService.calcularStats()).thenReturn(dnaStats);

        // When/Then
        mockMvc.perform(get("/api/v1/dna/stats/calcular/"))
                .andExpect(status().isOk());
    }
}
