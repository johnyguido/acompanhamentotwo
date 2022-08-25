package com.cs.acompanhamentotwo.services;

import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaSimplesResponseDTO;

import java.util.List;

public interface EnergiaService {
	
    List<EnergiaSimplesResponseDTO> listarTodasMedicoes();
    
	EnergiaRequestDTO gravarLeitura(EnergiaRequestDTO dto);
    
}
