package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.SegmentoAmostragemPCS;
import com.stfn2.ggas.domain.dtos.filter.SegmentoAmostragemPCSFilterDTO;
import jakarta.persistence.EntityManager;

public class SegmentoAmostragemPCSRepositoryImpl extends IRepository<SegmentoAmostragemPCS, SegmentoAmostragemPCSFilterDTO> {

    public SegmentoAmostragemPCSRepositoryImpl(EntityManager em) {
        super(em, "ORDER BY");
    }

    @Override
    protected void filters(SegmentoAmostragemPCSFilterDTO filter, ExecucaoQuery<SegmentoAmostragemPCS> execute) {

    }

}

