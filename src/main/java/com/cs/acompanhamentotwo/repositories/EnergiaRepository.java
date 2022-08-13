package com.cs.acompanhamentotwo.repositories;

import com.cs.acompanhamentotwo.model.entities.Energia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergiaRepository extends JpaRepository<Energia, Long> {
}
