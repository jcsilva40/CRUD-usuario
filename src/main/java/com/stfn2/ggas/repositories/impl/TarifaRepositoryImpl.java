package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.TarifaFilterDTO;
import com.stfn2.ggas.domain.Tarifa;

import jakarta.persistence.EntityManager;

public class TarifaRepositoryImpl extends IRepository<Tarifa, TarifaFilterDTO> {

	public TarifaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(TarifaFilterDTO filter, ExecucaoQuery<Tarifa> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

