package com.cs.acompanhamentotwo.mapper;

import com.cs.acompanhamentotwo.model.dto.UsuarioDTO;
import com.cs.acompanhamentotwo.model.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface UsuarioMapper {

    UsuarioDTO mapUsuarioEntityToUsuarioDTO(Usuario source);

}
