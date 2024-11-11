package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.CreditoDebitoDetalhamento;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoDetalhamentoFilterDTO;
import jakarta.persistence.EntityManager;

public class CreditoDebitoDetalhamentoRepositoryImpl extends IRepository<CreditoDebitoDetalhamento, CreditoDebitoDetalhamentoFilterDTO> {

	public CreditoDebitoDetalhamentoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(CreditoDebitoDetalhamentoFilterDTO filter, ExecucaoQuery<CreditoDebitoDetalhamento> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}