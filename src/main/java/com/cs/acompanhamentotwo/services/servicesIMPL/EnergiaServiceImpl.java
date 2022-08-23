package com.cs.acompanhamentotwo.services.servicesIMPL;

import com.cs.acompanhamentotwo.mapper.EnergiaMapper;
import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaResponseDTO;
import com.cs.acompanhamentotwo.model.entities.Energia;
import com.cs.acompanhamentotwo.repositories.EnergiaRepository;
import com.cs.acompanhamentotwo.services.EnergiaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnergiaServiceImpl implements EnergiaService {

    private final EnergiaRepository energiaRepository;

    private final EnergiaMapper energiaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<EnergiaResponseDTO> listarTodasMedicoes() {

    	log.info("Listando todas as medicoes...");
        List<Energia> medicoes = energiaRepository.findAll();

        return medicoes.stream()
                .map(energiaMapper::mapEnergiaResponseDtoToEnergia)
                .sorted((o1, o2) -> o2.getData().compareTo(o1.getData()) )
                .collect(Collectors.toList());
    }

	@Override
	@Transactional
	public EnergiaRequestDTO gravarLeitura(EnergiaRequestDTO dto) {

		Energia energia = new Energia();
		energia.setData(Instant.now());
		energia.setLeituraInicial(null);;
		energia.setTotal(obterTotal(dto.getLeituraFinal()));
		energia.setLeituraFinal(dto.getLeituraFinal());
		
		energia = energiaRepository.save(energia);
		
		return energiaMapper.mapEnergiaRequestDtoToEnergia(energia);
	}

	private Long obterTotal(Long leituraFinal) {
		// TODO Buscar a ultima medicao e somar com a atual
		return null;
	}
}
