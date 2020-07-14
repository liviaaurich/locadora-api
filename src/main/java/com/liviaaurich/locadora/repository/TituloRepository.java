package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {
    List<Titulo> findAllByDiretorId(Long id);

    List<Titulo> findAllByClasseId(Long id);
}
