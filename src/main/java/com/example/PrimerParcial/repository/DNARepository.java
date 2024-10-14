package com.example.PrimerParcial.repository;

import com.example.PrimerParcial.entities.DNA;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DNARepository  extends BaseRepository<DNA, Long> {
    Optional<DNA> findByDna(String dna);

    long countByIsMutant(boolean isMutant);

}

