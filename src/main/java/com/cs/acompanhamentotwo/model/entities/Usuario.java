package com.cs.acompanhamentotwo.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String sobrenome;

	@Column(unique = true)
	private String login;
	private String senha;

	@Email
	@Column(unique = true)
	private String email;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant criadoEm;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant atualizadoEm;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_USUARIO_PERFIS", joinColumns = @JoinColumn(name = "USUARIO_ID"), inverseJoinColumns = @JoinColumn(name = "PERFIS_ID"))
	private Set<Perfil> perfis = new HashSet<>();

	@PrePersist
	public void preCriacao() {
		criadoEm = Instant.now();
	}

	@PreUpdate
	public void preAtualizacao() {
		atualizadoEm = Instant.now();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
