package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.Ator;
import com.liviaaurich.locadora.service.dto.AtorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {

    @Query("SELECT new com.liviaaurich.locadora.service.dto.AtorDTO(a.id, a.nome) FROM Ator a" +
        " WHERE (:#{#filtro.nome} IS NULL OR LOWER(a.nome) LIKE LOWER(CONCAT(CONCAT('%', :#{#filtro.nome}), '%')))")
    Page<AtorDTO> findByFilter(@Param("filtro") AtorDTO dto, Pageable pageable);

}
