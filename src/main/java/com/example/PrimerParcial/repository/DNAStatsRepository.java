package com.example.PrimerParcial.repository;

import com.example.PrimerParcial.entities.DNAStats;
import org.springframework.stereotype.Repository;

@Repository
public interface DNAStatsRepository extends BaseRepository<DNAStats, Long> {
    /*@Query(
            value = "select count(*) from registro_dna where is_mutant = true",
            nativeQuery = true
    )
    Integer searchMutants();
    @Query(
            value = "select count(*) from registro_dna where is_mutant = false",
            nativeQuery = true
    )
    Integer searchNonMutants();

     */
}
