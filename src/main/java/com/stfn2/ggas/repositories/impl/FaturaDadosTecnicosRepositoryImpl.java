package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturaDadosTecnicosFilterDTO;
import com.stfn2.ggas.domain.FaturaDadosTecnicos;
import jakarta.persistence.EntityManager;

public class FaturaDadosTecnicosRepositoryImpl extends IRepository<FaturaDadosTecnicos, FaturaDadosTecnicosFilterDTO> {

	public FaturaDadosTecnicosRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(FaturaDadosTecnicosFilterDTO filter, ExecucaoQuery<FaturaDadosTecnicos> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
