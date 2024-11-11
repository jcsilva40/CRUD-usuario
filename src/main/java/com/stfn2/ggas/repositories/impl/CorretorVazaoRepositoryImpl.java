package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.CorretorVazaoFilterDTO;
import com.stfn2.ggas.domain.CorretorVazao;
import jakarta.persistence.EntityManager;

public class CorretorVazaoRepositoryImpl extends IRepository<CorretorVazao, CorretorVazaoFilterDTO> {

	public CorretorVazaoRepositoryImpl(EntityManager em) {
		super(em, "descricao");
	}

	@Override
	protected void filters(CorretorVazaoFilterDTO filter, ExecucaoQuery<CorretorVazao> execute) {
		 addFilterLike(execute, "descricao", filter.getDescricao());
	}
}