package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorContratoConvenioFilterDTO;
import com.stfn2.ggas.domain.ArrecadadorContratoConvenio;
import jakarta.persistence.EntityManager;

public class ArrecadadorContratoConvenioRepositoryImpl extends IRepository<ArrecadadorContratoConvenio, ArrecadadorContratoConvenioFilterDTO> {

	public ArrecadadorContratoConvenioRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ArrecadadorContratoConvenioFilterDTO filter, ExecucaoQuery<ArrecadadorContratoConvenio> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}