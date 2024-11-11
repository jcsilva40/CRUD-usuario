package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ContaBancariaFilterDTO;
import com.stfn2.ggas.domain.ContaBancaria;
import jakarta.persistence.EntityManager;

public class ContaBancariaRepositoryImpl extends IRepository<ContaBancaria, ContaBancariaFilterDTO> {

	public ContaBancariaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ContaBancariaFilterDTO filter, ExecucaoQuery<ContaBancaria> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}