package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.LancamentoItemContabilFilterDTO;
import com.stfn2.ggas.domain.LancamentoItemContabil;
import jakarta.persistence.EntityManager;

public class LancamentoItemContabilRepositoryImpl extends IRepository<LancamentoItemContabil, LancamentoItemContabilFilterDTO> {

	public LancamentoItemContabilRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(LancamentoItemContabilFilterDTO filter, ExecucaoQuery<LancamentoItemContabil> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}