package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ContratoPontoConsumoItemFaturamentoFilterDTO;
import com.stfn2.ggas.domain.ContratoPontoConsumoItemFaturamento;
import jakarta.persistence.EntityManager;

public class ContratoPontoConsumoItemFaturamentoRepositoryImpl extends IRepository<ContratoPontoConsumoItemFaturamento, ContratoPontoConsumoItemFaturamentoFilterDTO> {

	public ContratoPontoConsumoItemFaturamentoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ContratoPontoConsumoItemFaturamentoFilterDTO filter, ExecucaoQuery<ContratoPontoConsumoItemFaturamento> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}