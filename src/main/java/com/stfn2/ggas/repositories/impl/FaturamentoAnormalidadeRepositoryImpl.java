package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoAnormalidadeFilterDTO;
import com.stfn2.ggas.domain.FaturamentoAnormalidade;
import jakarta.persistence.EntityManager;

public class FaturamentoAnormalidadeRepositoryImpl extends IRepository<FaturamentoAnormalidade, FaturamentoAnormalidadeFilterDTO> {

	public FaturamentoAnormalidadeRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturamentoAnormalidadeFilterDTO filter, ExecucaoQuery<FaturamentoAnormalidade> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}