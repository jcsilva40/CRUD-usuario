package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ContratoPontoConsumoPCSAmostragemFilterDTO;
import com.stfn2.ggas.domain.ContratoPontoConsumoPCSAmostragem;
import jakarta.persistence.EntityManager;

public class ContratoPontoConsumoPCSAmostragemRepositoryImpl extends IRepository<ContratoPontoConsumoPCSAmostragem, ContratoPontoConsumoPCSAmostragemFilterDTO> {

	public ContratoPontoConsumoPCSAmostragemRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ContratoPontoConsumoPCSAmostragemFilterDTO filter, ExecucaoQuery<ContratoPontoConsumoPCSAmostragem> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}