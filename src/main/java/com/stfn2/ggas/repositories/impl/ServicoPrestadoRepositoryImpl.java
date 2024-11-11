package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ServicoPrestadoFilterDTO;
import com.stfn2.ggas.domain.ServicoPrestado;

import jakarta.persistence.EntityManager;

public class ServicoPrestadoRepositoryImpl extends IRepository<ServicoPrestado, ServicoPrestadoFilterDTO> {

	public ServicoPrestadoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(ServicoPrestadoFilterDTO filter, ExecucaoQuery<ServicoPrestado> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

