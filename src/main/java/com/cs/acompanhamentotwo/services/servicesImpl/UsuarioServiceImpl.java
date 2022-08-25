package com.cs.acompanhamentotwo.services.servicesImpl;

import com.cs.acompanhamentotwo.model.dto.UsuarioDTO;
import com.cs.acompanhamentotwo.model.entities.Usuario;
import com.cs.acompanhamentotwo.repositories.UsuarioRepository;
import com.cs.acompanhamentotwo.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Page<UsuarioDTO> findAllPaged(Pageable pageable) {
        Page<Usuario> list = usuarioRepository.findAll(pageable);
        return list.map(x -> new UsuarioDTO(x));
    }
}
