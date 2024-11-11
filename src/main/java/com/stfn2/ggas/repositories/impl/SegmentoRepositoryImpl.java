package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.Segmento;
import com.stfn2.ggas.domain.dtos.filter.SegmentoFilterDTO;
import jakarta.persistence.EntityManager;

public class SegmentoRepositoryImpl extends IRepository<Segmento, SegmentoFilterDTO> {

    public SegmentoRepositoryImpl(EntityManager em) {
        super(em, "descricao");
    }

    @Override
    protected void filters(SegmentoFilterDTO segmentoFilterDTO, ExecucaoQuery<Segmento> execute) {

    }
}

