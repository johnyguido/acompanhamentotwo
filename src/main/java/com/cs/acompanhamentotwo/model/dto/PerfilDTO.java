package com.cs.acompanhamentotwo.model.dto;

import com.cs.acompanhamentotwo.model.entities.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {

	private Long id;
	private String permissao;

	public PerfilDTO(Perfil perfil) {
		id = perfil.getId();
		permissao = perfil.getPermissao();
	}

}
