package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.RamoAtividadeSubstituicaoTributariaFilterDTO;
import com.stfn2.ggas.domain.RamoAtividadeSubstituicaoTributaria;
import jakarta.persistence.EntityManager;

public class RamoAtividadeSubstituicaoTributariaRepositoryImpl extends IRepository<RamoAtividadeSubstituicaoTributaria, RamoAtividadeSubstituicaoTributariaFilterDTO> {

    public RamoAtividadeSubstituicaoTributariaRepositoryImpl(EntityManager em) {
        super(em, "id");
    }

    @Override
    protected void filters(RamoAtividadeSubstituicaoTributariaFilterDTO filter, ExecucaoQuery<RamoAtividadeSubstituicaoTributaria> execute) {
        //addFilterPositive(execute, "id", filter.getId());
    }
}