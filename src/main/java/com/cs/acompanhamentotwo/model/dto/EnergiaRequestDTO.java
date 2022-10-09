package com.cs.acompanhamentotwo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergiaRequestDTO {

    @NotNull(message = "Obrigatório inserir uma valor")
    private Long leituraFinal;

}
