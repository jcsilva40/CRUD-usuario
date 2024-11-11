package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturaTributacaoFilterDTO;
import com.stfn2.ggas.domain.FaturaTributacao;
import jakarta.persistence.EntityManager;

public class FaturaTributacaoRepositoryImpl extends IRepository<FaturaTributacao, FaturaTributacaoFilterDTO> {

	public FaturaTributacaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturaTributacaoFilterDTO filter, ExecucaoQuery<FaturaTributacao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}