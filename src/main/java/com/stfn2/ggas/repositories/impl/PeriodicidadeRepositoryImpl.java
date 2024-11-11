package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.Periodicidade;
import com.stfn2.ggas.domain.dtos.filter.PeriodicidadeFilterDTO;
import jakarta.persistence.EntityManager;

public class PeriodicidadeRepositoryImpl extends IRepository<Periodicidade, PeriodicidadeFilterDTO> {

    public PeriodicidadeRepositoryImpl(EntityManager em) {
        super(em, "descricao");
    }


    @Override
    protected void filters(PeriodicidadeFilterDTO filter, ExecucaoQuery<Periodicidade> execute) {
        addFilterPositive(execute, "quantidadeDias", filter.getQuantidadeDias());
    }

}
