package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByTituloId(Long id);
}
