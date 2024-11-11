package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturaItemTributacaoFilterDTO;
import com.stfn2.ggas.domain.FaturaItemTributacao;
import jakarta.persistence.EntityManager;

public class FaturaItemTributacaoRepositoryImpl extends IRepository<FaturaItemTributacao, FaturaItemTributacaoFilterDTO> {

	public FaturaItemTributacaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturaItemTributacaoFilterDTO filter, ExecucaoQuery<FaturaItemTributacao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}