package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AtorRepository extends JpaRepository<Ator, Long>, JpaSpecificationExecutor<Ator> {
}
