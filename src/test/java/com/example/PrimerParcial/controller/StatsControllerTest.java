package com.example.PrimerParcial.controller;

import com.example.PrimerParcial.entities.DNAStats;
import com.example.PrimerParcial.service.DNAStatsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatsController.class)
public class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DNAStatsServiceImpl dnaStatsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalcularStatsError() throws Exception {
        //Simulamos una excepción en el servicio
        when(dnaStatsService.calcularStats()).thenThrow(new RuntimeException("Error calculating stats"));

        //R ealizamos la solicitud GET y verificamos la respuesta
        mockMvc.perform(get("/api/v1/dna/stats/calcular/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error calculating stats"));
    }
}
