package com.liviaaurich.locadora.repository;

import com.liviaaurich.locadora.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
}
