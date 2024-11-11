package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturaImpressaoFilterDTO;
import com.stfn2.ggas.domain.FaturaImpressao;
import jakarta.persistence.EntityManager;

public class FaturaImpressaoRepositoryImpl extends IRepository<FaturaImpressao, FaturaImpressaoFilterDTO> {

	public FaturaImpressaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturaImpressaoFilterDTO filter, ExecucaoQuery<FaturaImpressao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}