package com.cs.acompanhamentotwo.repositories;

import com.cs.acompanhamentotwo.model.entities.Energia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnergiaRepository extends JpaRepository<Energia, Long> {

	@Query("Select obj FROM Energia obj WHERE obj.data >= :minDate AND obj.data <= :maxDate and user_id = :id")
	Page<Energia> buscarMedicoesPorUsuarioPaginadas(Instant minDate, Instant maxDate, Pageable pageable, long id);

	Optional<Energia> findFirstByUsuarioIdOrderByDataDesc(Long id);

	List<Energia> findAllByUsuarioId(Long id);

	Page<Energia> findAllByUsuarioId(Pageable pageable,Long Id);
}
