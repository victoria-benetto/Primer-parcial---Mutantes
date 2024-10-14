package com.example.PrimerParcial.service;

import com.example.PrimerParcial.entities.DNAStats;

public interface DNAStatsService extends BaseService<DNAStats, Long> {
    public DNAStats calcularStats() throws Exception;
}
