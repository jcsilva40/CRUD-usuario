package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturaItemFilterDTO;
import com.stfn2.ggas.domain.FaturaItem;
import jakarta.persistence.EntityManager;

public class FaturaItemRepositoryImpl extends IRepository<FaturaItem, FaturaItemFilterDTO> {

	public FaturaItemRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturaItemFilterDTO filter, ExecucaoQuery<FaturaItem> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}