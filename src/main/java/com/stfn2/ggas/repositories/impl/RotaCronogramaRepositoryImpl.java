package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.RotaCronogramaFilterDTO;
import com.stfn2.ggas.domain.RotaCronograma;
import jakarta.persistence.EntityManager;

public class RotaCronogramaRepositoryImpl extends IRepository<RotaCronograma, RotaCronogramaFilterDTO> {

	public RotaCronogramaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(RotaCronogramaFilterDTO filter, ExecucaoQuery<RotaCronograma> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
