package com.liviaaurich.locadora.service.filtros;

import com.liviaaurich.locadora.domain.Ator_;
import com.liviaaurich.locadora.domain.Titulo_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TituloFiltro implements EntityFilter {

    private String nome;
    private String ano;
    private String diretor;
    private String categoria;
    private String classe;

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
        if (!StringUtils.isEmpty(nome)) {
            Predicate predicate = cb.like(cb.lower(root.get(Titulo_.nome)), "%" + nome.toLowerCase() + "%");
            predicates.add(predicate);
        }
        if (!StringUtils.isEmpty(ano)) {
            Predicate predicate = cb.like(cb.lower(root.get(Titulo_.ano)), "%" + ano.toLowerCase() + "%");
            predicates.add(predicate);
        }
        if (!StringUtils.isEmpty(diretor)) {
            Predicate predicate = cb.like(cb.lower(root.get(Titulo_.diretor)), "%" + diretor.toLowerCase() + "%");
            predicates.add(predicate);
        }
        if (!StringUtils.isEmpty(categoria)) {
            Predicate predicate = cb.like(cb.lower(root.get(Titulo_.categoria)), "%" + categoria.toLowerCase() + "%");
            predicates.add(predicate);
        }
        if (!StringUtils.isEmpty(classe)) {
            Predicate predicate = cb.like(cb.lower(root.get(Titulo_.classe)), "%" + classe.toLowerCase() + "%");
            predicates.add(predicate);
        }
        return predicates;
    }
}
