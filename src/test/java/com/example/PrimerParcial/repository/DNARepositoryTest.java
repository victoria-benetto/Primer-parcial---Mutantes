package com.example.PrimerParcial.repository;
import com.example.PrimerParcial.entities.DNA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DNARepositoryTest {

    @Autowired
    private DNARepository dnaRepository;

    @Test
    void shouldSaveAndFindDna() {
        //Given
        DNA dna = new DNA();
        dna.setDna("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG");
        dna.setMutant(true);

        //When
        DNA savedDna = dnaRepository.save(dna);

        //Then
        assertThat(savedDna.getId()).isNotNull();
        assertThat(savedDna.isMutant()).isTrue();
        assertThat(savedDna.getDna()).isEqualTo("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG");
    }

    @Test
    void shouldFindDnaByDnaSequence() {
        // Given
        DNA dna = new DNA();
        dna.setDna("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG");
        dna.setMutant(true);
        dnaRepository.save(dna);

        //When
        Optional<DNA> foundDna = dnaRepository.findByDna(dna.getDna());

        //Then
        assertThat(foundDna).isPresent();
        assertThat(foundDna.get().getDna()).isEqualTo(dna.getDna());
    }
}
