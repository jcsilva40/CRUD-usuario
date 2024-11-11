package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemVencimentFilterDTO;
import com.stfn2.ggas.domain.FaturamentoMensagemVenciment;
import jakarta.persistence.EntityManager;

public class FaturamentoMensagemVencimentRepositoryImpl extends IRepository<FaturamentoMensagemVenciment, FaturamentoMensagemVencimentFilterDTO> {

	public FaturamentoMensagemVencimentRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturamentoMensagemVencimentFilterDTO filter, ExecucaoQuery<FaturamentoMensagemVenciment> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}