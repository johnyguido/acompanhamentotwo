package com.cs.acompanhamentotwo.repositories;

import com.cs.acompanhamentotwo.model.entities.Energia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnergiaRepository extends JpaRepository<Energia, Long> {

	Optional<Energia> findFirstByUsuarioIdOrderByDataDesc(Long id);

	List<Energia> findAllByUsuarioId(Long id);

	Page<Energia> findAllByUsuarioId(Pageable pageable,Long Id);
}
