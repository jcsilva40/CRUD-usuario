package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturaGeralFilterDTO;
import com.stfn2.ggas.domain.FaturaGeral;
import jakarta.persistence.EntityManager;

public class FaturaGeralRepositoryImpl extends IRepository<FaturaGeral, FaturaGeralFilterDTO> {

	public FaturaGeralRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturaGeralFilterDTO filter, ExecucaoQuery<FaturaGeral> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}