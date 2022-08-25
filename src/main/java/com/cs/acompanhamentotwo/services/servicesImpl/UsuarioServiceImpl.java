package com.cs.acompanhamentotwo.services.servicesImpl;

import com.cs.acompanhamentotwo.mapper.UsuarioMapper;
import com.cs.acompanhamentotwo.model.dto.UsuarioDTO;
import com.cs.acompanhamentotwo.model.entities.Usuario;
import com.cs.acompanhamentotwo.repositories.UsuarioRepository;
import com.cs.acompanhamentotwo.services.UsuarioService;
import com.cs.acompanhamentotwo.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    @Override
    public Page<UsuarioDTO> findAllPaged(Pageable pageable) {
        Page<Usuario> list = usuarioRepository.findAll(pageable);
        return list.map(x -> new UsuarioDTO(x));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public UsuarioDTO findById(Long id) {
        log.info("Buscando o usuario de id {}...",id);
        Optional<Usuario> obj = usuarioRepository.findById(id);
        Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
        return usuarioMapper.mapUsuarioEntityToUsuarioDTO(entity);
    }
}
