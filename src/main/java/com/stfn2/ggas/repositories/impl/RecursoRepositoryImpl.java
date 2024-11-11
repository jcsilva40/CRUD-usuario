package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.RecursoFilterDTO;
import com.stfn2.ggas.domain.Recurso;
import jakarta.persistence.EntityManager;

public class RecursoRepositoryImpl extends IRepository<Recurso, RecursoFilterDTO> {

	public RecursoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(RecursoFilterDTO filter, ExecucaoQuery<Recurso> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
