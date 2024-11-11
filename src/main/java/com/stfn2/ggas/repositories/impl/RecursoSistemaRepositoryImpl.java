package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.RecursoSistemaFilterDTO;
import com.stfn2.ggas.domain.RecursoSistema;
import jakarta.persistence.EntityManager;

public class RecursoSistemaRepositoryImpl extends IRepository<RecursoSistema, RecursoSistemaFilterDTO> {

	public RecursoSistemaRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(RecursoSistemaFilterDTO filter, ExecucaoQuery<RecursoSistema> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}