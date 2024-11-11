package com.stfn2.ggas.core.capsulas;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExecucaoQuery<Entidade> {

    CriteriaQuery<Entidade> criteria;

    Root<Entidade> iRoot;

    List<Predicate> predicates = new ArrayList<>();


    public ExecucaoQuery(CriteriaQuery<Entidade> criteria, Root<Entidade> iRoot) {
        this.criteria = criteria;
        this.iRoot = iRoot;
        this.predicates = predicates;
    }


}
