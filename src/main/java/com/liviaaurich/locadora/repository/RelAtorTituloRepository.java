package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.AtorTituloPK;
import com.liviaaurich.locadora.domain.RelAtorTitulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelAtorTituloRepository extends JpaRepository<RelAtorTitulo, AtorTituloPK> {
    Boolean existsByIdIdAtor(Long id);
}
