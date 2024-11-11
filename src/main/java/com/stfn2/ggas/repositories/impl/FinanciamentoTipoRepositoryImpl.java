package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FinanciamentoTipoFilterDTO;
import com.stfn2.ggas.domain.FinanciamentoTipo;

import jakarta.persistence.EntityManager;

public class FinanciamentoTipoRepositoryImpl extends IRepository<FinanciamentoTipo, FinanciamentoTipoFilterDTO> {

	public FinanciamentoTipoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FinanciamentoTipoFilterDTO filter, ExecucaoQuery<FinanciamentoTipo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

