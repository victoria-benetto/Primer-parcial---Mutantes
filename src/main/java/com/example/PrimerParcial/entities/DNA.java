package com.example.PrimerParcial.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Dna")

public class DNA extends Base {

    @Column(name = "dna")
    private String dna;

    @Column(name = "isMutant")
    private boolean isMutant;

}
