package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.NaturezaOperacaoCfopFilterDTO;
import com.stfn2.ggas.domain.NaturezaOperacaoCfop;
import jakarta.persistence.EntityManager;

public class NaturezaOperacaoCfopRepositoryImpl extends IRepository<NaturezaOperacaoCfop, NaturezaOperacaoCfopFilterDTO> {

	public NaturezaOperacaoCfopRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(NaturezaOperacaoCfopFilterDTO filter, ExecucaoQuery<NaturezaOperacaoCfop> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}