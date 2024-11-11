package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.EntidadeConteudo;
import com.stfn2.ggas.domain.dtos.filter.EntidadeConteudoFilterDTO;
import jakarta.persistence.EntityManager;

public class EntidadeConteudoRepositoryImpl extends IRepository<EntidadeConteudo, EntidadeConteudoFilterDTO> {

    public EntidadeConteudoRepositoryImpl(EntityManager em) {
        super(em, "abreviado");
    }


    @Override
    protected void filters(EntidadeConteudoFilterDTO filter, ExecucaoQuery<EntidadeConteudo> execute) {
        addFilterLike(execute, "abreviado", filter.getAbreviado());
    }

}
