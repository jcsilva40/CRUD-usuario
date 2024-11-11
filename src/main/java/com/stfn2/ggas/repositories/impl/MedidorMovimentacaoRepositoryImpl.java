package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.MedidorMovimentacaoFilterDTO;
import com.stfn2.ggas.domain.MedidorMovimentacao;
import jakarta.persistence.EntityManager;

public class MedidorMovimentacaoRepositoryImpl extends IRepository<MedidorMovimentacao, MedidorMovimentacaoFilterDTO> {

	public MedidorMovimentacaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(MedidorMovimentacaoFilterDTO filter, ExecucaoQuery<MedidorMovimentacao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}