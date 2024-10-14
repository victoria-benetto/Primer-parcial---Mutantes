package com.example.PrimerParcial.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stats")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DNAStats extends Base{

    @Column(name = "mutantCount")
    private long mutantCount;

    @Column(name = "nonMutantCount")
    private long nonMutantCount;

    @Column(name = "mutantRatio")
    private double mutantRatio;

}
