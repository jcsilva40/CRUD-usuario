package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DevolucaoFilterDTO;
import com.stfn2.ggas.domain.Devolucao;
import jakarta.persistence.EntityManager;

public class DevolucaoRepositoryImpl extends IRepository<Devolucao, DevolucaoFilterDTO> {

	public DevolucaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(DevolucaoFilterDTO filter, ExecucaoQuery<Devolucao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}