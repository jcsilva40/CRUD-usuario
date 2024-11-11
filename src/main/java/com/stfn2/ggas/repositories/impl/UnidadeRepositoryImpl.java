package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.UnidadeFilterDTO;
import com.stfn2.ggas.domain.Unidade;
import jakarta.persistence.EntityManager;

public class UnidadeRepositoryImpl extends IRepository<Unidade, UnidadeFilterDTO> {

	public UnidadeRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(UnidadeFilterDTO filter, ExecucaoQuery<Unidade> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}