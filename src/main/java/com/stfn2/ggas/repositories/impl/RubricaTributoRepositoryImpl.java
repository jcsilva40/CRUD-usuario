package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.RubricaTributoFilterDTO;
import com.stfn2.ggas.domain.RubricaTributo;

import jakarta.persistence.EntityManager;

public class RubricaTributoRepositoryImpl extends IRepository<RubricaTributo, RubricaTributoFilterDTO> {

	public RubricaTributoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(RubricaTributoFilterDTO filter, ExecucaoQuery<RubricaTributo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

