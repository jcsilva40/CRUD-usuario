package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.BoletoFilterDTO;
import com.stfn2.ggas.domain.Boleto;
import jakarta.persistence.EntityManager;

public class BoletoRepositoryImpl extends IRepository<Boleto, BoletoFilterDTO> {

	public BoletoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(BoletoFilterDTO filter, ExecucaoQuery<Boleto> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
