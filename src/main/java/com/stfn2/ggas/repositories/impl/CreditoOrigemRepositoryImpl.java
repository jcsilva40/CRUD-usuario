package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.CreditoOrigemFilterDTO;
import com.stfn2.ggas.domain.CreditoOrigem;
import jakarta.persistence.EntityManager;

public class CreditoOrigemRepositoryImpl extends IRepository<CreditoOrigem, CreditoOrigemFilterDTO> {

	public CreditoOrigemRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(CreditoOrigemFilterDTO filter, ExecucaoQuery<CreditoOrigem> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}