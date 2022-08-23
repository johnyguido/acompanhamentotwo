package com.cs.acompanhamentotwo.services;

import com.cs.acompanhamentotwo.model.dto.EnergiaRequestDTO;
import com.cs.acompanhamentotwo.model.dto.EnergiaResponseDTO;

import java.util.List;

public interface EnergiaService {
	
    List<EnergiaResponseDTO> listarTodasMedicoes();
    
	EnergiaRequestDTO gravarLeitura(EnergiaRequestDTO dto);
    
}
