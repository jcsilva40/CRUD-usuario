package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemFilterDTO;
import com.stfn2.ggas.domain.FaturamentoMensagem;
import jakarta.persistence.EntityManager;

public class FaturamentoMensagemRepositoryImpl extends IRepository<FaturamentoMensagem, FaturamentoMensagemFilterDTO> {

	public FaturamentoMensagemRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturamentoMensagemFilterDTO filter, ExecucaoQuery<FaturamentoMensagem> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}