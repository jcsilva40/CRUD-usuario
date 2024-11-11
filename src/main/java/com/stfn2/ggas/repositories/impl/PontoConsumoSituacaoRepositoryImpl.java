package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoSituacaoFilterDTO;
import com.stfn2.ggas.domain.PontoConsumoSituacao;

import jakarta.persistence.EntityManager;

public class PontoConsumoSituacaoRepositoryImpl extends IRepository<PontoConsumoSituacao, PontoConsumoSituacaoFilterDTO> {

	public PontoConsumoSituacaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(PontoConsumoSituacaoFilterDTO filter, ExecucaoQuery<PontoConsumoSituacao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

