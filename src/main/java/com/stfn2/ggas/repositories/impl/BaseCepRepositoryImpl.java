package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.BaseCepFilterDTO;
import com.stfn2.ggas.domain.BaseCep;

import jakarta.persistence.EntityManager;

public class BaseCepRepositoryImpl extends IRepository<BaseCep, BaseCepFilterDTO> {

		public BaseCepRepositoryImpl(EntityManager em) {
		super(em, "nome");
	}

	@Override
	protected void filters(BaseCepFilterDTO filter, ExecucaoQuery<BaseCep> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}


