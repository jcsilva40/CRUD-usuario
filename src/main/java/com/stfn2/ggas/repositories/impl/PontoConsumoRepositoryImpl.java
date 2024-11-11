package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoFilterDTO;
import com.stfn2.ggas.domain.PontoConsumo;

import jakarta.persistence.EntityManager;

public class PontoConsumoRepositoryImpl extends IRepository<PontoConsumo, PontoConsumoFilterDTO> {

	public PontoConsumoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(PontoConsumoFilterDTO filter, ExecucaoQuery<PontoConsumo> execute) {
		addFilter(execute, "cil", filter.getCil());
	}
}

