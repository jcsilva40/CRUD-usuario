package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.NomenclaturaComumMercosulFilterDTO;
import com.stfn2.ggas.domain.NomenclaturaComumMercosul;

import jakarta.persistence.EntityManager;

public class NomenclaturaComumMercosulRepositoryImpl extends IRepository<NomenclaturaComumMercosul, NomenclaturaComumMercosulFilterDTO> {

	public NomenclaturaComumMercosulRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(NomenclaturaComumMercosulFilterDTO filter, ExecucaoQuery<NomenclaturaComumMercosul> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

