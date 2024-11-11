package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoNegociadoFilterDTO;
import com.stfn2.ggas.domain.CreditoDebitoNegociado;
import jakarta.persistence.EntityManager;

public class CreditoDebitoNegociadoRepositoryImpl extends IRepository<CreditoDebitoNegociado, CreditoDebitoNegociadoFilterDTO> {

	public CreditoDebitoNegociadoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(CreditoDebitoNegociadoFilterDTO filter, ExecucaoQuery<CreditoDebitoNegociado> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}