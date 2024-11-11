package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.RamoAtividade;
import com.stfn2.ggas.domain.dtos.filter.RamoAtividadeFilterDTO;
import jakarta.persistence.EntityManager;

public class RamoAtividadeRepositoryImpl extends IRepository<RamoAtividade, RamoAtividadeFilterDTO> {

    public RamoAtividadeRepositoryImpl(EntityManager em) {
        super(em, "id");
    }

    @Override
    protected void filters(RamoAtividadeFilterDTO filter, ExecucaoQuery<RamoAtividade> execute) {
        //addFilterPositive(execute, "id", filter.getId());
    }
}
