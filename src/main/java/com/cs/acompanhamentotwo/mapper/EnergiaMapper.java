package com.cs.acompanhamentotwo.mapper;

import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaResponseDTO;
import com.cs.acompanhamentotwo.model.entities.Energia;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.time.Instant;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface EnergiaMapper {

    EnergiaRequestDTO mapEnergiaRequestDtoToEnergia(Energia source);
    
    EnergiaResponseDTO mapEnergiaResponseDtoToEnergia(Energia source);

    Energia mapEnergiaRequestToEnergiaEntity(EnergiaRequestDTO source);

    default Energia mapEntidadeParaSalvar(EnergiaRequestDTO source, Long medicaoAnterior, Long total) {
        Energia medicaoParaSalvar = Energia.builder()
                .leituraFinal(source.getLeituraFinal())
                .data(Instant.now())
                .leituraInicial(medicaoAnterior)
                .total(total)
                .leituraFinal(source.getLeituraFinal()).build();
        return medicaoParaSalvar;
    }
}
