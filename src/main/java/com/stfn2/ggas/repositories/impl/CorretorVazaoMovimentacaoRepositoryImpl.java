package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.CorretorVazaoMovimentacaoFilterDTO;
import com.stfn2.ggas.domain.CorretorVazaoMovimentacao;
import jakarta.persistence.EntityManager;

public class CorretorVazaoMovimentacaoRepositoryImpl extends IRepository<CorretorVazaoMovimentacao, CorretorVazaoMovimentacaoFilterDTO> {

	public CorretorVazaoMovimentacaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(CorretorVazaoMovimentacaoFilterDTO filter, ExecucaoQuery<CorretorVazaoMovimentacao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}