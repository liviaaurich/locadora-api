package com.liviaaurich.locadora.service.filtros;

import com.liviaaurich.locadora.domain.Ator_;
import com.liviaaurich.locadora.domain.Item_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ItemFiltro implements EntityFilter {

    private Long id;
    private String tipo;
    private String dataAquisicao;
    private String titulo;

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
            Predicate predicate = cb.equal(root.get(Item_.id), id);
            predicates.add(predicate);
        }
        if (!StringUtils.isEmpty(tipo)) {
            Predicate predicate = cb.like(cb.lower(root.get(Item_.tipoItem)), "%" + tipo.toLowerCase() + "%");
            predicates.add(predicate);
        }
        if (!StringUtils.isEmpty(dataAquisicao)) {
            Predicate predicate = cb.like(cb.lower(root.get(Item_.dataAquisicao)), "%" + dataAquisicao.toLowerCase() + "%");
            predicates.add(predicate);
        }
        if (!StringUtils.isEmpty(titulo)) {
            Predicate predicate = cb.like(cb.lower(root.get(Item_.titulo)), "%" + titulo.toLowerCase() + "%");
            predicates.add(predicate);
        }
        return predicates;
    }
}
