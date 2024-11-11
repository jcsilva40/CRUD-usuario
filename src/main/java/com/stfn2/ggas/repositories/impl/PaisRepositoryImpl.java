package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.PaisFilterDTO;
import com.stfn2.ggas.domain.Pais;

import jakarta.persistence.EntityManager;

public class PaisRepositoryImpl extends IRepository<Pais, PaisFilterDTO> {

	public PaisRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(PaisFilterDTO filter, ExecucaoQuery<Pais> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

