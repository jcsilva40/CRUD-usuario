package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ConstanteSistemaFilterDTO;
import com.stfn2.ggas.domain.ConstanteSistema;
import jakarta.persistence.EntityManager;

public class ConstanteSistemaRepositoryImpl extends IRepository<ConstanteSistema, ConstanteSistemaFilterDTO> {

	public ConstanteSistemaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ConstanteSistemaFilterDTO filter, ExecucaoQuery<ConstanteSistema> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
