package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.IndiceFinanceiroValorNominal;
import com.stfn2.ggas.domain.dtos.filter.IndiceFinanceiroValorNominalFilterDTO;
import jakarta.persistence.EntityManager;

public class IndiceFinanceiroValorNominalRepositoryImpl extends IRepository<IndiceFinanceiroValorNominal, IndiceFinanceiroValorNominalFilterDTO> {

    public IndiceFinanceiroValorNominalRepositoryImpl(EntityManager em) {
        super(em, "dataReferencia");
    }


    @Override
    protected void filters(IndiceFinanceiroValorNominalFilterDTO filter, ExecucaoQuery<IndiceFinanceiroValorNominal> execute) {
        addFilter(execute, "dataReferencia", filter.getDataReferencia());
        addFilterPositiveFk(execute, "indiceFinanceiro", filter.getIdIndice());
    }

}
