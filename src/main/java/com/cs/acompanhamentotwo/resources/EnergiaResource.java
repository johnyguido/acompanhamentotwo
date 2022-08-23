package com.cs.acompanhamentotwo.resources;

import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaResponseDTO;
import com.cs.acompanhamentotwo.services.EnergiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/medicoes")
@RequiredArgsConstructor
public class EnergiaResource {

    private final EnergiaService energiaService;

    @GetMapping
    public ResponseEntity<List<EnergiaResponseDTO>> findAll() {
        return ResponseEntity.ok().body(energiaService.listarTodasMedicoes());
    }
    
    @PostMapping
	public ResponseEntity<EnergiaRequestDTO> insert(@RequestBody EnergiaRequestDTO dto) {
    	EnergiaRequestDTO requestDTO = energiaService.gravarLeitura(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getLeituraFinal()).toUri();
		return ResponseEntity.created(uri).body(requestDTO);
	}

}
