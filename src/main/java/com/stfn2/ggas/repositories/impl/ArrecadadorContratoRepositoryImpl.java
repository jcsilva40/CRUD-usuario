package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorContratoFilterDTO;
import com.stfn2.ggas.domain.ArrecadadorContrato;
import jakarta.persistence.EntityManager;

public class ArrecadadorContratoRepositoryImpl extends IRepository<ArrecadadorContrato, ArrecadadorContratoFilterDTO> {

	public ArrecadadorContratoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ArrecadadorContratoFilterDTO filter, ExecucaoQuery<ArrecadadorContrato> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}