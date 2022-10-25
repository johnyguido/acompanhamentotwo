package com.cs.acompanhamentotwo.mapper;

import com.cs.acompanhamentotwo.model.dto.RegistroAcessoResponseDTO;
import com.cs.acompanhamentotwo.model.entities.RegistroAcessoUsuario;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface RegistroAcessoMapper {

    RegistroAcessoResponseDTO mapRegistroAcessoToDTO(RegistroAcessoUsuario source);

    RegistroAcessoUsuario mapRegistroAcessoDTOToEntity(RegistroAcessoResponseDTO source);

}
