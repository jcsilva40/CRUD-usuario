package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.LiberacaoCronogramaFaturamento;
import com.stfn2.ggas.domain.dtos.filter.LiberacaoCronogramaFaturamentoFilterDTO;
import jakarta.persistence.EntityManager;

public class LiberacaoCronogramaFaturamentoRepositoryImpl extends IRepository<LiberacaoCronogramaFaturamento, LiberacaoCronogramaFaturamentoFilterDTO> {

    public LiberacaoCronogramaFaturamentoRepositoryImpl(EntityManager em) {
        super(em, "id");
    }


    @Override
    protected void filters(LiberacaoCronogramaFaturamentoFilterDTO filter, ExecucaoQuery<LiberacaoCronogramaFaturamento> execute) {
        addFilterPositive(execute, "anoMes", filter.getAnoMes());
        addFilterPositive(execute, "ciclo", filter.getCiclo());
        addFilter(execute, "status", filter.getStatus());
        if(filter.getFaturamentoGrupoId() != null){
            addFilterSubAttribute(execute, "faturamentoGrupo", "id", filter.getFaturamentoGrupoId());
        }
    }
}
