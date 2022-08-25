package com.cs.acompanhamentotwo.mapper;

import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSimplesResponseDTO;
import com.cs.acompanhamentotwo.model.entities.Energia;
import com.cs.acompanhamentotwo.model.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.time.Instant;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface EnergiaMapper {

    EnergiaRequestDTO mapEnergiaEntityToEnergiaRequestDto(Energia source);
    
    EnergiaSimplesResponseDTO mapEnergiaResponseDtoToEnergia(Energia source);

    default Energia mapEntidadeParaSalvar(EnergiaRequestDTO source, Long medicaoAnterior, Long total) {
        Energia medicaoParaSalvar = Energia.builder()
                .leituraFinal(source.getLeituraFinal())
                .data(Instant.now())
                .leituraInicial(medicaoAnterior)
                .total(total)
                .leituraFinal(source.getLeituraFinal())
                //TODO: Refatorar para incluir id do usuario Logado sem Mock
                .usuario(Usuario.builder().id(1L).build())
                .build();
        return medicaoParaSalvar;
    }
}
