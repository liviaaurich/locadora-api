package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.Dependente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long> {
    Page<Dependente> findAllBySocioId(Long id, Pageable pageable);

    Long countBySocioIdAndStatus(Long id, Boolean status);
}
