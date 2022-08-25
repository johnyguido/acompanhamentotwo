package com.cs.acompanhamentotwo.services.servicesImpl;

import com.cs.acompanhamentotwo.mapper.EnergiaMapper;
import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSimplesResponseDTO;
import com.cs.acompanhamentotwo.model.entities.Energia;
import com.cs.acompanhamentotwo.repositories.EnergiaRepository;
import com.cs.acompanhamentotwo.services.EnergiaService;
import com.cs.acompanhamentotwo.services.exceptions.MedicaoNaoRealizadaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<EnergiaSimplesResponseDTO> listarTodasMedicoes() {

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

		Long medicaoAnterior = buscarMedicaoAnterior();
		Long total = obterTotal(medicaoAnterior, dto);

		if (medicaoInvalida(medicaoAnterior, dto)) {
			throw new MedicaoNaoRealizadaException("Medicao invalida");
		}  

		log.info("Salvando a medicao...");
		Energia energia = energiaRepository.save(energiaMapper.mapEntidadeParaSalvar(dto, medicaoAnterior, total));

		return energiaMapper.mapEnergiaEntityToEnergiaRequestDto(energia);
	}

	private Long obterTotal(Long medicaoAnterior, EnergiaRequestDTO dto) {
		return dto.getLeituraFinal() - medicaoAnterior;
	}

	private boolean medicaoInvalida(Long medicaoAnterior, EnergiaRequestDTO dto) {
		if (dto.getLeituraFinal() < medicaoAnterior) {
			return true;
		}
		return false;
	}
	private Long buscarMedicaoAnterior() {
		log.info("Buscando a medicao anterior...");
		Energia energia = energiaRepository.findFirstByOrderByDataDesc();
		return energia.getLeituraFinal();
	}
}
