package com.cs.acompanhamentotwo.services.servicesImpl;

import com.cs.acompanhamentotwo.mapper.EnergiaMapper;
import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaResponseDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSimplesResponseDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSomaDTO;
import com.cs.acompanhamentotwo.model.entities.Energia;
import com.cs.acompanhamentotwo.model.entities.Usuario;
import com.cs.acompanhamentotwo.repositories.EnergiaRepository;
import com.cs.acompanhamentotwo.services.EnergiaService;
import com.cs.acompanhamentotwo.services.UsuarioService;
import com.cs.acompanhamentotwo.services.exceptions.MedicaoNaoRealizadaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnergiaServiceImpl implements EnergiaService {

    private final EnergiaRepository energiaRepository;

    private final UsuarioService usuarioService;

    private final EnergiaMapper energiaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<EnergiaSimplesResponseDTO> listarTodasMedicoes() {

        log.info("Listando todas as medicoes...");
        List<Energia> medicoes = energiaRepository.findAll();
        return medicoes.stream()
                .map(energiaMapper::mapEnergiaResponseDtoToEnergia)
                .sorted((o1, o2) -> o2.getData().compareTo(o1.getData()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EnergiaResponseDTO gravarLeitura(EnergiaRequestDTO dto) {

        Long id = obterUsuarioAutenticado().getId();
        Long medicaoAnterior = buscarMedicaoAnterior(id);

        log.info("Salvando a medicao...");
        Energia energia = energiaRepository.save(energiaMapper
				.mapEntidadeParaSalvar(dto, medicaoAnterior, validaMedicao(dto, medicaoAnterior), obterUsuarioAutenticado()));

        return energiaMapper.mapEnergiaToEnergiaResponseDto(energia);
    }

    private Long validaMedicao(EnergiaRequestDTO dto, Long medicaoAnterior) {

        Long total = obterTotal(medicaoAnterior, dto);

        if (medicaoInvalida(medicaoAnterior, dto)) {
            throw new MedicaoNaoRealizadaException("Consumo anterior " + medicaoAnterior + " kWH.");
        } else return total;
    }

    /**
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<EnergiaSomaDTO> somaMensal() {
        List<Energia> medicoes = energiaRepository.findAll();

        List<Long> soma = Collections.singletonList(medicoes.stream()
                .filter(energia -> mesAtual(energia.getData()))
                .collect(Collectors.summingLong(Energia::getTotal)));

        return soma.stream().map(EnergiaSomaDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<EnergiaSimplesResponseDTO> buscarTodasMedicoesPorUsuarioLogado() {
        return energiaRepository.findAllByUsuarioId(obterIdUsuarioAutenticado())
                .stream()
                .map(energiaMapper::mapEnergiaResponseDtoToEnergia)
                .collect(Collectors.toList());
    }

    private boolean mesAtual(Instant data) {
        int mesAtual = Instant
				.now()
				.atOffset(ZoneOffset.UTC)
				.getMonth()
				.getValue();

        int mesDaLeitura = data
				.atZone(ZoneId.systemDefault())
				.getMonthValue();

        if (mesDaLeitura >= mesAtual) {
            return Boolean.TRUE;
        } else
			return Boolean.FALSE;
    }

    private Long obterTotal(Long medicaoAnterior, EnergiaRequestDTO dto) {
        return ObjectUtils.nullSafeHashCode(dto.getLeituraFinal()) - medicaoAnterior;
    }

    private boolean medicaoInvalida(Long medicaoAnterior, EnergiaRequestDTO dto) {
        if (ObjectUtils.isEmpty(dto.getLeituraFinal()) || dto.getLeituraFinal() <= medicaoAnterior) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Long buscarMedicaoAnterior(Long id) {
        log.info("Obtendo a medicao anterior...");

        Optional<Energia> energia = energiaRepository
				.findFirstByUsuarioIdOrderByDataDesc(id);

        if (ObjectUtils.isEmpty(energia)) {
            return 0L;
        } else
            return energia.get().getLeituraFinal();
    }

    private Authentication obterUsuarioLogado() {
        Authentication autenticacao = SecurityContextHolder
				.getContext()
				.getAuthentication();
        return autenticacao;
    }

    private Usuario obterUsuarioAutenticado() {

		Authentication autenticacao = SecurityContextHolder
				.getContext()
				.getAuthentication();

        Usuario usuarioAutenticado = usuarioService
				.findByEmail(autenticacao.getName());
        return usuarioAutenticado;
    }

    private Long obterIdUsuarioAutenticado() {

		Authentication autenticacao = SecurityContextHolder
				.getContext()
				.getAuthentication();

        Usuario usuarioAutenticado = usuarioService
				.findByEmail(SecurityContextHolder
						.getContext()
						.getAuthentication()
						.getName());

		return usuarioAutenticado.getId();
    }

}