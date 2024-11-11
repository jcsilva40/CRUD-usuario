package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ParametroSistemaFilterDTO;
import com.stfn2.ggas.domain.ParametroSistema;
import jakarta.persistence.EntityManager;

public class ParametroSistemaRepositoryImpl extends IRepository<ParametroSistema, ParametroSistemaFilterDTO> {

	public ParametroSistemaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ParametroSistemaFilterDTO filter, ExecucaoQuery<ParametroSistema> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
