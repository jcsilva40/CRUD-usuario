package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.RubricaValorRegulamentadoFilterDTO;
import com.stfn2.ggas.domain.RubricaValorRegulamentado;

import jakarta.persistence.EntityManager;

public class RubricaValorRegulamentadoRepositoryImpl extends IRepository<RubricaValorRegulamentado, RubricaValorRegulamentadoFilterDTO> {

	public RubricaValorRegulamentadoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(RubricaValorRegulamentadoFilterDTO filter, ExecucaoQuery<RubricaValorRegulamentado> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

