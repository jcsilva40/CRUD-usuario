package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturaItemImpressaoFilterDTO;
import com.stfn2.ggas.domain.FaturaItemImpressao;
import jakarta.persistence.EntityManager;

public class FaturaItemImpressaoRepositoryImpl extends IRepository<FaturaItemImpressao, FaturaItemImpressaoFilterDTO> {

	public FaturaItemImpressaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturaItemImpressaoFilterDTO filter, ExecucaoQuery<FaturaItemImpressao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}