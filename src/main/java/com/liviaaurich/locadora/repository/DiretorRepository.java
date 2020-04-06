package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.Diretor;
import com.liviaaurich.locadora.service.dto.DiretorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiretorRepository extends JpaRepository<Diretor, Long>, JpaSpecificationExecutor<Diretor> {

    @Query("SELECT new com.liviaaurich.locadora.service.dto.DiretorDTO(d.id, d.nome) FROM Diretor d " +
        " WHERE (:#{#filtro.nome} IS NULL OR LOWER(d.nome) LIKE LOWER(CONCAT(CONCAT('%', :#{#filtro.nome}), '%')))")
    Page<DiretorDTO> findByFilter(@Param("filtro") DiretorDTO dto, Pageable pageable);

}
