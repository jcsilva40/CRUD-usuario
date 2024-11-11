package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturaHistoricoFilterDTO;
import com.stfn2.ggas.domain.FaturaHistorico;
import jakarta.persistence.EntityManager;

public class FaturaHistoricoRepositoryImpl extends IRepository<FaturaHistorico, FaturaHistoricoFilterDTO> {

	public FaturaHistoricoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(FaturaHistoricoFilterDTO filter, ExecucaoQuery<FaturaHistorico> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
