package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.LeituraTipoFilterDTO;
import com.stfn2.ggas.domain.LeituraTipo;

import jakarta.persistence.EntityManager;

public class LeituraTipoRepositoryImpl extends IRepository<LeituraTipo, LeituraTipoFilterDTO> {

	public LeituraTipoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(LeituraTipoFilterDTO filter, ExecucaoQuery<LeituraTipo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

