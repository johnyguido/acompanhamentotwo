package com.cs.acompanhamentotwo.repositories;

import com.cs.acompanhamentotwo.model.dto.UsuarioDTO;
import com.cs.acompanhamentotwo.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByEmail(String email);

}
