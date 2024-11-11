package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorCarteiraCobrancaFilterDTO;
import com.stfn2.ggas.domain.ArrecadadorCarteiraCobranca;
import jakarta.persistence.EntityManager;

public class ArrecadadorCarteiraCobrancaRepositoryImpl extends IRepository<ArrecadadorCarteiraCobranca, ArrecadadorCarteiraCobrancaFilterDTO> {

	public ArrecadadorCarteiraCobrancaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ArrecadadorCarteiraCobrancaFilterDTO filter, ExecucaoQuery<ArrecadadorCarteiraCobranca> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}