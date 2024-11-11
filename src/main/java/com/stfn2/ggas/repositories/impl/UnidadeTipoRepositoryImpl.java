package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.UnidadeTipoFilterDTO;
import com.stfn2.ggas.domain.UnidadeTipo;

import jakarta.persistence.EntityManager;

public class UnidadeTipoRepositoryImpl extends IRepository<UnidadeTipo, UnidadeTipoFilterDTO> {

	public UnidadeTipoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(UnidadeTipoFilterDTO filter, ExecucaoQuery<UnidadeTipo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

