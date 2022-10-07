package com.cs.acompanhamentotwo.services.servicesImpl;

import com.cs.acompanhamentotwo.mapper.UsuarioMapper;
import com.cs.acompanhamentotwo.model.dto.UsuarioDTO;
import com.cs.acompanhamentotwo.model.entities.Usuario;
import com.cs.acompanhamentotwo.repositories.UsuarioRepository;
import com.cs.acompanhamentotwo.services.UsuarioService;
import com.cs.acompanhamentotwo.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);
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

    @Override
    public Usuario findByEmail(String email) {
        log.info("Buscando usuario de email: {}", email);
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);

        if (usuario == null) {
            logger.error("Usuario não encontrado " + username);
            throw new UsernameNotFoundException("E-mail inexistente");
        }
        logger.info("Usuario autenticado " + username);
        return usuario;
    }
}
