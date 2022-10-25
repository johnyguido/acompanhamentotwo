package com.cs.acompanhamentotwo.repositories;

import com.cs.acompanhamentotwo.model.entities.RegistroAcessoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistroAcessoRepository extends JpaRepository<RegistroAcessoUsuario, Long> {

    Optional<RegistroAcessoUsuario> findByUsuarioIdAndIsUltimoAcessoIsTrue(Long idUsuario);



}
