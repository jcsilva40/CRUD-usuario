package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.AgenciaFilterDTO;
import com.stfn2.ggas.domain.Agencia;
import jakarta.persistence.EntityManager;

public class AgenciaRepositoryImpl extends IRepository<Agencia, AgenciaFilterDTO> {

	public AgenciaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(AgenciaFilterDTO filter, ExecucaoQuery<Agencia> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}