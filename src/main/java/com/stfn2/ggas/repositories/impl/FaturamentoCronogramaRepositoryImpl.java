package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoCronogramaFilterDTO;
import com.stfn2.ggas.domain.FaturamentoCronograma;
import jakarta.persistence.EntityManager;

public class FaturamentoCronogramaRepositoryImpl extends IRepository<FaturamentoCronograma, FaturamentoCronogramaFilterDTO> {

	public FaturamentoCronogramaRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturamentoCronogramaFilterDTO filter, ExecucaoQuery<FaturamentoCronograma> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}