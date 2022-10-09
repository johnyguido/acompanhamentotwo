package com.cs.acompanhamentotwo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergiaRequestDTO {

    @NotNull(message = "Obrigatório inserir um valor")
    @Positive(message = "Somente valores positivos")
    private Long leituraFinal;

}
