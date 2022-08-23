package com.cs.acompanhamentotwo.model.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_energia")
public class Energia  {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long leituraInicial;

    private Long leituraFinal;

    private Long total;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant data;

}
