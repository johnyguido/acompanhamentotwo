package com.cs.acompanhamentotwo.model.dto;

import com.cs.acompanhamentotwo.model.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroAcessoResponseDTO {

    private Long id;
    private Usuario usuario;
    private Instant dataAcesso;
    private Boolean isUltimoAcesso;

}
