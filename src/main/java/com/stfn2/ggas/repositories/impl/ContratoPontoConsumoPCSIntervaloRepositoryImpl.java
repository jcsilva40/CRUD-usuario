package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ContratoPontoConsumoPCSIntervaloFilterDTO;
import com.stfn2.ggas.domain.ContratoPontoConsumoPCSIntervalo;
import jakarta.persistence.EntityManager;

public class ContratoPontoConsumoPCSIntervaloRepositoryImpl extends IRepository<ContratoPontoConsumoPCSIntervalo, ContratoPontoConsumoPCSIntervaloFilterDTO> {

	public ContratoPontoConsumoPCSIntervaloRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ContratoPontoConsumoPCSIntervaloFilterDTO filter, ExecucaoQuery<ContratoPontoConsumoPCSIntervalo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}