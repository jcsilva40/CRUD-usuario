package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.BIStefaniniFilterDTO;
import com.stfn2.ggas.domain.BIStefanini;
import jakarta.persistence.EntityManager;

public class BIStefaniniRepositoryImpl extends IRepository<BIStefanini, BIStefaniniFilterDTO> {

	public BIStefaniniRepositoryImpl(EntityManager em) {
		super(em, "descricao");
	}

	@Override
	protected void filters(BIStefaniniFilterDTO filter, ExecucaoQuery<BIStefanini> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
