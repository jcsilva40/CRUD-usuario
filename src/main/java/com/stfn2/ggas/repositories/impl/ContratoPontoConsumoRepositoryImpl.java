package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ContratoPontoConsumoFilterDTO;
import com.stfn2.ggas.domain.ContratoPontoConsumo;
import jakarta.persistence.EntityManager;

public class ContratoPontoConsumoRepositoryImpl extends IRepository<ContratoPontoConsumo, ContratoPontoConsumoFilterDTO> {

	public ContratoPontoConsumoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ContratoPontoConsumoFilterDTO filter, ExecucaoQuery<ContratoPontoConsumo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}