package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.TributoFilterDTO;
import com.stfn2.ggas.domain.Tributo;

import jakarta.persistence.EntityManager;

public class TributoRepositoryImpl extends IRepository<Tributo, TributoFilterDTO> {

	public TributoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(TributoFilterDTO filter, ExecucaoQuery<Tributo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

