package com.cs.acompanhamentotwo.resources;

import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaResponseDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSimplesResponseDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSomaDTO;
import com.cs.acompanhamentotwo.services.EnergiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping(value = "/medicoes")
@RequiredArgsConstructor
public class EnergiaResource {

    private final EnergiaService energiaService;

    @GetMapping(value = "/paginadas")
    public ResponseEntity<Page<EnergiaResponseDTO>> findAllPaginada(

            @RequestParam(value = "min", defaultValue = "") String min,
            @RequestParam(value = "max", defaultValue = "") String max,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "0") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "data") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {

        Instant minDate = ("".equals(min)) ? Instant.now().minus(30, ChronoUnit.DAYS) : Instant.parse(min);
        Instant maxDate = ("".equals(max)) ? Instant.now() : Instant.parse(max);

        if (linesPerPage == 0) {
            linesPerPage = Integer.MAX_VALUE;
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return ResponseEntity.ok().body(energiaService.buscarMedicoesPorUsuarioPaginadas(minDate, maxDate, pageRequest));

    }

    @GetMapping
    public ResponseEntity<Page<EnergiaSimplesResponseDTO>> findAll(Pageable pageable) {
        Page<EnergiaSimplesResponseDTO> list = energiaService.listarTodasMedicoes(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/porusuario")
    public ResponseEntity<List<EnergiaSimplesResponseDTO>> buscarTodasMedicoesPorUsuarioLogado() {
        return ResponseEntity.ok().body(energiaService.buscarTodasMedicoesPorUsuarioLogado());
    }

    @GetMapping(value = "/mensal")
    public ResponseEntity<List<EnergiaSomaDTO>> somaMensal() {

        List<EnergiaSomaDTO> list = energiaService.somaMensal();
        return ResponseEntity.ok(list);

    }
    
    @PostMapping
	public ResponseEntity<EnergiaResponseDTO> insert(@RequestBody @Valid EnergiaRequestDTO dto) {
        EnergiaResponseDTO energiaResponseDTO = energiaService.gravarLeitura(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getLeituraFinal()).toUri();
		return ResponseEntity.created(uri).body(energiaResponseDTO);
	}

}
