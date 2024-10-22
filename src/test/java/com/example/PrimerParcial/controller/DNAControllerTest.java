package com.example.PrimerParcial.controller;


import com.example.PrimerParcial.dto.DNARequest;
import com.example.PrimerParcial.entities.DNA;
import com.example.PrimerParcial.service.DNAService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DNAController.class)
public class DNAControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DNAService dnaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDetectMutant() throws Exception {
        DNARequest dnaRequest = new DNARequest();
        dnaRequest.setDna(List.of("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"));

        when(dnaService.isMutant(any(DNA.class))).thenReturn(true);

        mockMvc.perform(post("/api/v1/dna/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dnaRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"mutant\":\"Mutante encontrado.\"}"));
    }

    @Test
    public void testDetectNotMutant() throws Exception {
        DNARequest dnaRequest = new DNARequest();
        dnaRequest.setDna(List.of("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"));

        when(dnaService.isMutant(any(DNA.class))).thenReturn(false);

        mockMvc.perform(post("/api/v1/dna/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dnaRequest)))
                .andExpect(status().isForbidden())
                .andExpect(content().json("{\"mutant\":\"Mutante no encontrado.\"}"));
    }

    @Test
    public void testDetectMutantException() throws Exception {
        DNARequest dnaRequest = new DNARequest();
        dnaRequest.setDna(List.of("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"));

        when(dnaService.isMutant(any(DNA.class))).thenThrow(new Exception("Error interno"));

        mockMvc.perform(post("/api/v1/dna/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dnaRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Error interno\"}"));
    }
}
