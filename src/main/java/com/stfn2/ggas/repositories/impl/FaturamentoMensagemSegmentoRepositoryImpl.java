package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoMensagemSegmentoFilterDTO;
import com.stfn2.ggas.domain.FaturamentoMensagemSegmento;
import jakarta.persistence.EntityManager;

public class FaturamentoMensagemSegmentoRepositoryImpl extends IRepository<FaturamentoMensagemSegmento, FaturamentoMensagemSegmentoFilterDTO> {

	public FaturamentoMensagemSegmentoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturamentoMensagemSegmentoFilterDTO filter, ExecucaoQuery<FaturamentoMensagemSegmento> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}