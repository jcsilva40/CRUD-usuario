package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.AtividadeSistemaFilterDTO;
import com.stfn2.ggas.domain.AtividadeSistema;
import jakarta.persistence.EntityManager;

public class AtividadeSistemaRepositoryImpl extends IRepository<AtividadeSistema, AtividadeSistemaFilterDTO> {

	public AtividadeSistemaRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(AtividadeSistemaFilterDTO filter, ExecucaoQuery<AtividadeSistema> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}