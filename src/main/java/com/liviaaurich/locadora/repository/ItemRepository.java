package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByTituloId(Long id);
}
