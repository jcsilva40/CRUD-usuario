package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.PressaoFornecimentoFaixaFilterDTO;
import com.stfn2.ggas.domain.PressaoFornecimentoFaixa;
import jakarta.persistence.EntityManager;

public class PressaoFornecimentoFaixaRepositoryImpl extends IRepository<PressaoFornecimentoFaixa, PressaoFornecimentoFaixaFilterDTO> {

	public PressaoFornecimentoFaixaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(PressaoFornecimentoFaixaFilterDTO filter, ExecucaoQuery<PressaoFornecimentoFaixa> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}