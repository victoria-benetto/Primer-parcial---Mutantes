package com.example.PrimerParcial.service;

import com.example.PrimerParcial.entities.DNAStats;
import com.example.PrimerParcial.repository.BaseRepository;
import com.example.PrimerParcial.repository.DNARepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DNAStatsServiceImpl extends BaseServiceImpl<DNAStats, Long> implements DNAStatsService{
    @Autowired
    private DNARepository dnaRepository;

    public DNAStatsServiceImpl(BaseRepository<DNAStats, Long> baseRepository){
        super(baseRepository);
    }

    @Override
    @Transactional
    public DNAStats calcularStats() throws Exception{
        long contadorADNMutante = dnaRepository.countByIsMutant(true);
        long contadorADNHumano = dnaRepository.countByIsMutant(false);
        double ratio;
        if (contadorADNHumano > 0) {
            ratio = (double)contadorADNMutante / contadorADNHumano;
        } else{
            ratio = 0;
        }
        return new DNAStats(contadorADNMutante, contadorADNHumano, ratio);
    }
}
