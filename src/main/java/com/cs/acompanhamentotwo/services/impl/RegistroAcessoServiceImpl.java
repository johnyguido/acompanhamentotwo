package com.cs.acompanhamentotwo.services.impl;

import com.cs.acompanhamentotwo.mapper.RegistroAcessoMapper;
import com.cs.acompanhamentotwo.model.dto.RegistroAcessoResponseDTO;
import com.cs.acompanhamentotwo.model.entities.RegistroAcessoUsuario;
import com.cs.acompanhamentotwo.model.entities.Usuario;
import com.cs.acompanhamentotwo.repositories.RegistroAcessoRepository;
import com.cs.acompanhamentotwo.repositories.UsuarioRepository;
import com.cs.acompanhamentotwo.services.RegistroAcessoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RegistroAcessoServiceImpl implements RegistroAcessoService {

    private final RegistroAcessoRepository registroAcessoRepository;

    private final UsuarioRepository usuarioRepository;

    private final RegistroAcessoMapper registroAcessoMapper;

    @Override
    public RegistroAcessoResponseDTO gravarAcesso(Long idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario id " +idUsuario+" nao encontrado!"));

       Optional<RegistroAcessoUsuario> ultimoAcesso = registroAcessoRepository.findByUsuarioIdAndIsUltimoAcessoIsTrue(idUsuario);

       RegistroAcessoUsuario registroAcessoUsuario;

       if (ultimoAcesso.isEmpty()){
           registroAcessoUsuario = registroAcessoRepository
                   .save(RegistroAcessoUsuario.builder().usuario(usuario).build());
           return registroAcessoMapper.mapRegistroAcessoToDTO(registroAcessoUsuario);
       }else

           registroAcessoUsuario =  registroAcessoRepository.save(RegistroAcessoUsuario
                   .builder()
                   .id(ultimoAcesso
                           .get()
                           .getId())
                   .isUltimoAcesso(false)
                           .usuario(usuario)
                           .dataAcesso(ultimoAcesso.get().getDataAcesso())
                   .build());

         registroAcessoRepository
                .save(RegistroAcessoUsuario.builder().usuario(usuario).build());

        return registroAcessoMapper.mapRegistroAcessoToDTO(registroAcessoUsuario);


    }
}
