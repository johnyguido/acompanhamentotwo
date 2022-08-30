package com.cs.acompanhamentotwo.resources;

import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSimplesResponseDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSomaDTO;
import com.cs.acompanhamentotwo.services.EnergiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/medicoes")
@RequiredArgsConstructor
public class EnergiaResource {

    private final EnergiaService energiaService;

    @GetMapping
    public ResponseEntity<List<EnergiaSimplesResponseDTO>> findAll() {
        return ResponseEntity.ok().body(energiaService.listarTodasMedicoes());
    }

    @GetMapping(value = "/mensal")
    public ResponseEntity<List<EnergiaSomaDTO>> somaMensal() {

        List<EnergiaSomaDTO> list = energiaService.somaMensal();
        return ResponseEntity.ok(list);

    }
    
    @PostMapping
	public ResponseEntity<EnergiaRequestDTO> insert(@RequestBody EnergiaRequestDTO dto) {
    	EnergiaRequestDTO requestDTO = energiaService.gravarLeitura(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getLeituraFinal()).toUri();
		return ResponseEntity.created(uri).body(requestDTO);
	}

}
