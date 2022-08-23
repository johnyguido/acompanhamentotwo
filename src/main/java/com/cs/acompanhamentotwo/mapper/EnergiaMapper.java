package com.cs.acompanhamentotwo.mapper;

import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaResponseDTO;
import com.cs.acompanhamentotwo.model.entities.Energia;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface EnergiaMapper {

    EnergiaRequestDTO mapEnergiaRequestDtoToEnergia(Energia source);
    
    EnergiaResponseDTO mapEnergiaResponseDtoToEnergia(Energia source);
    
    Energia mapEnergiaRequestToEnergiaEntity(EnergiaRequestDTO source);

}
