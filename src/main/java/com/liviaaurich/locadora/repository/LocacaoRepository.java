package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    List<Locacao> findByItemIdAndDtDevolucaoPrevistaAfter(Long id, LocalDateTime dataLocacao);

    Boolean existsByItemId(Long id);

    Boolean existsBySocioId(Long id);

    Boolean existsByDependenteId(Long id);

    Boolean existsByDependenteSocioId(Long id);
}
