package com.cs.acompanhamentotwo.services;

import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaResponseDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSimplesResponseDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSomaDTO;

import java.util.List;

public interface EnergiaService {
	
    List<EnergiaSimplesResponseDTO> listarTodasMedicoes();
    
	EnergiaResponseDTO gravarLeitura(EnergiaRequestDTO dto);

    List<EnergiaSomaDTO> somaMensal();

    List<EnergiaSimplesResponseDTO> buscarTodasMedicoesPorUsuarioLogado();
    
}
