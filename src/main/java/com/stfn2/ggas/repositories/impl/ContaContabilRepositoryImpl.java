package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.ContaContabil;
import com.stfn2.ggas.domain.dtos.filter.ContaContabilFilterDTO;
import jakarta.persistence.EntityManager;

public class ContaContabilRepositoryImpl extends IRepository<ContaContabil, ContaContabilFilterDTO> {

    public ContaContabilRepositoryImpl(EntityManager em) {
        super(em, "ORDER BY");
    }

    @Override
    protected void filters(ContaContabilFilterDTO filter, ExecucaoQuery<ContaContabil> execute) {
        addFilter(execute, "mensal", filter.getMensal());
    }

}

