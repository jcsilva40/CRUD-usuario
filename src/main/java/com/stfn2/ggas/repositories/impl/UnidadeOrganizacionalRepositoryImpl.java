package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.UnidadeOrganizacionalFilterDTO;
import com.stfn2.ggas.domain.UnidadeOrganizacional;

import jakarta.persistence.EntityManager;

public class UnidadeOrganizacionalRepositoryImpl extends IRepository<UnidadeOrganizacional, UnidadeOrganizacionalFilterDTO> {

	public UnidadeOrganizacionalRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(UnidadeOrganizacionalFilterDTO filter, ExecucaoQuery<UnidadeOrganizacional> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

