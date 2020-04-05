package com.liviaaurich.locadora.service.filtros;

import com.liviaaurich.locadora.domain.Ator_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AtorFiltro implements EntityFilter {

    private Long id;
    private String nome;

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification filter() {
        return (root, cq, cb) ->
            cb.and(getPredicates(root, cb).toArray(new Predicate[0]));
    }

    /**
     * @param root root
     * @param cb cb
     * @return List Predicate
     */
    private List<Predicate> getPredicates(Root root, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(id)) {
            Predicate predicate = cb.like(cb.lower(root.get(Ator_.nome)), "%" + nome.toLowerCase() + "%");
            predicates.add(predicate);
        }
        if (!StringUtils.isEmpty(nome)) {
            Predicate predicate = cb.equal(root.get(Ator_.id), id);
            predicates.add(predicate);
        }
        return predicates;
    }
}
