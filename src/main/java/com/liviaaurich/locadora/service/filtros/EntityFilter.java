package com.liviaaurich.locadora.service.filtros;

import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("rawtypes")
public interface EntityFilter {
    Specification filter();
}
