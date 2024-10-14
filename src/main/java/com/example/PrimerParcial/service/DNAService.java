package com.example.PrimerParcial.service;

import com.example.PrimerParcial.entities.DNA;

public interface DNAService extends BaseService<DNA, Long>{
    public boolean isMutant(DNA dna) throws Exception;
}
