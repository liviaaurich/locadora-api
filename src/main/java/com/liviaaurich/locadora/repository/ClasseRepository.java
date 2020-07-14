package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.Classe;
import com.liviaaurich.locadora.service.dto.ClasseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClasseRepository extends JpaRepository<Classe, Long>, JpaSpecificationExecutor<Classe> {

    @Query("SELECT new com.liviaaurich.locadora.service.dto.ClasseDTO(c.id, c.nome, c.valor, c.prazoDevolucao) FROM Classe c" +
        " WHERE (:#{#filtro.nome} IS NULL OR LOWER(c.nome) LIKE LOWER(CONCAT(CONCAT('%', :#{#filtro.nome}), '%')))")
    Page<ClasseDTO> findByFilter(@Param("filtro") ClasseDTO dto, Pageable pageable);
}
