package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.MunicipioFilterDTO;
import com.stfn2.ggas.domain.Municipio;

import jakarta.persistence.EntityManager;

public class MunicipioRepositoryImpl extends IRepository<Municipio, MunicipioFilterDTO> {

	public MunicipioRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(MunicipioFilterDTO filter, ExecucaoQuery<Municipio> execute) {
		addFilterPositiveFk(execute, "uf", filter.getIdUf());
	}
}

