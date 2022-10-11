package com.cs.acompanhamentotwo.services;

import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaResponseDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSimplesResponseDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSomaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnergiaService {

    Page<EnergiaSimplesResponseDTO> listarTodasMedicoes(Pageable pageable);
    
	EnergiaResponseDTO gravarLeitura(EnergiaRequestDTO dto);

    List<EnergiaSomaDTO> somaMensal();

    List<EnergiaSimplesResponseDTO> buscarTodasMedicoesPorUsuarioLogado();
    
}
