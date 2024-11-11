package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ContratoClienteFilterDTO;
import com.stfn2.ggas.domain.ContratoCliente;
import jakarta.persistence.EntityManager;

public class ContratoClienteRepositoryImpl extends IRepository<ContratoCliente, ContratoClienteFilterDTO> {

	public ContratoClienteRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ContratoClienteFilterDTO filter, ExecucaoQuery<ContratoCliente> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}