package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.IndiceFinanceiro;
import com.stfn2.ggas.domain.dtos.filter.IndiceFinanceiroFilterDTO;
import jakarta.persistence.EntityManager;

public class IndiceFinanceiroRepositoryImpl extends IRepository<IndiceFinanceiro, IndiceFinanceiroFilterDTO> {

    public IndiceFinanceiroRepositoryImpl(EntityManager em) {
        super(em, "abreviado");
    }


    @Override
    protected void filters(IndiceFinanceiroFilterDTO filter, ExecucaoQuery<IndiceFinanceiro> execute) {
        addFilter(execute, "mensal", filter.getMensal());
    }
}
