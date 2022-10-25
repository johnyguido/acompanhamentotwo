package com.cs.acompanhamentotwo.model.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Table(name = "REGISTRO_ACESSO_USUARIO")
public class RegistroAcessoUsuario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataAcesso;

    private boolean isUltimoAcesso;

    @PrePersist
    public void preAcesso() {
        dataAcesso = Instant.now();
        isUltimoAcesso = true;
    }

}
