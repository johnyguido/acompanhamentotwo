package com.cs.acompanhamentotwo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergiaResponseDTO  {

    private Long leituraInicial;
    private Long leituraFinal;
    private Long total;
    private Instant data;

}
