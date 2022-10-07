package com.cs.acompanhamentotwo.repositories;

import com.cs.acompanhamentotwo.model.entities.Energia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnergiaRepository extends JpaRepository<Energia, Long> {

	Optional<Energia> findFirstByOrderByDataDesc();

	List<Energia> findAllByUsuarioId(Long id);
}
