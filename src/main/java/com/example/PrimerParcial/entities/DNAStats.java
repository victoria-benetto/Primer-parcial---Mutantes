package com.example.PrimerParcial.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DNAStats extends Base{

    private long mutantCount;

    private long nonMutantCount;

    private double mutantRatio;

}
