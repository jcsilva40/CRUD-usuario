package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ContratoFilterDTO;
import com.stfn2.ggas.domain.Contrato;
import jakarta.persistence.EntityManager;

public class ContratoRepositoryImpl extends IRepository<Contrato, ContratoFilterDTO> {

	public ContratoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ContratoFilterDTO filter, ExecucaoQuery<Contrato> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}