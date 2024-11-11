package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.LeituristaFilterDTO;
import com.stfn2.ggas.domain.Leiturista;

import jakarta.persistence.EntityManager;

public class LeituristaRepositoryImpl extends IRepository<Leiturista, LeituristaFilterDTO> {

	public LeituristaRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(LeituristaFilterDTO filter, ExecucaoQuery<Leiturista> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

