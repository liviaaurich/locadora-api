package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.Titulo;
import com.liviaaurich.locadora.service.dto.AtorDTO;
import com.liviaaurich.locadora.service.dto.TituloDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TituloRepository extends JpaRepository<Titulo, Long> {
    List<Titulo> findAllByDiretorId(Long id);

    List<Titulo> findAllByClasseId(Long id);
}
