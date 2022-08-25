package com.cs.acompanhamentotwo.model.dto;

import com.cs.acompanhamentotwo.model.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSimplesDTO {

	private Long id;

	@NotBlank(message = "Campo obrigatorio")
	private String nome;

	@NotBlank(message = "Campo obrigatorio")
	private String sobrenome;

	@NotBlank(message = "Campo obrigatorio")
	private String login;

	@NotBlank(message = "Campo obrigatorio")
	@Email(message = "Campo obrigatorio")
	private String email;

	//private Set<PerfilDTO> perfis = new HashSet<>();

	public UsuarioSimplesDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.login = usuario.getLogin();
		this.email = usuario.getEmail();
	//	usuario.getPerfis().forEach(perfil -> this.perfis.add(new PerfilDTO(perfil)));
	}
}
