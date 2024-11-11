package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemCobrancaFilterDTO;
import com.stfn2.ggas.domain.FaturamentoMensagemCobranca;
import jakarta.persistence.EntityManager;

public class FaturamentoMensagemCobrancaRepositoryImpl extends IRepository<FaturamentoMensagemCobranca, FaturamentoMensagemCobrancaFilterDTO> {

	public FaturamentoMensagemCobrancaRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturamentoMensagemCobrancaFilterDTO filter, ExecucaoQuery<FaturamentoMensagemCobranca> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}