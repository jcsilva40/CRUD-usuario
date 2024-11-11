package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.TarifaPontoConsumoFilterDTO;
import com.stfn2.ggas.domain.TarifaPontoConsumo;

import jakarta.persistence.EntityManager;

public class TarifaPontoConsumoRepositoryImpl extends IRepository<TarifaPontoConsumo, TarifaPontoConsumoFilterDTO> {

	public TarifaPontoConsumoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(TarifaPontoConsumoFilterDTO filter, ExecucaoQuery<TarifaPontoConsumo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

