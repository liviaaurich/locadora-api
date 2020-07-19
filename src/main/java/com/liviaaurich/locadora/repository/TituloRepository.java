package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {
    Boolean existsByDiretorId(Long id);

    Boolean existsByClasseId(Long id);
}
