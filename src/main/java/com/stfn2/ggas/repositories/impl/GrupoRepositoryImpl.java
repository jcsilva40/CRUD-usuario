package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.GrupoFilterDTO;
import com.stfn2.ggas.domain.Grupo;
import jakarta.persistence.EntityManager;

public class GrupoRepositoryImpl extends IRepository<Grupo, GrupoFilterDTO> {

	public GrupoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(GrupoFilterDTO filter, ExecucaoQuery<Grupo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
