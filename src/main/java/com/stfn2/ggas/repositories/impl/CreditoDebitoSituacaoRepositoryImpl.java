package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoSituacaoFilterDTO;
import com.stfn2.ggas.domain.CreditoDebitoSituacao;
import jakarta.persistence.EntityManager;

public class CreditoDebitoSituacaoRepositoryImpl extends IRepository<CreditoDebitoSituacao, CreditoDebitoSituacaoFilterDTO> {

	public CreditoDebitoSituacaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(CreditoDebitoSituacaoFilterDTO filter, ExecucaoQuery<CreditoDebitoSituacao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}