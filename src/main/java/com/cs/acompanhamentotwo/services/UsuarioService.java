package com.cs.acompanhamentotwo.services;

import com.cs.acompanhamentotwo.model.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    Page<UsuarioDTO> findAllPaged(Pageable pageable) ;

}
