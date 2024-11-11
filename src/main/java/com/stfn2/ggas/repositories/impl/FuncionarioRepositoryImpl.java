package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FuncionarioFilterDTO;
import com.stfn2.ggas.domain.Funcionario;

import jakarta.persistence.EntityManager;

public class FuncionarioRepositoryImpl extends IRepository<Funcionario, FuncionarioFilterDTO> {

	public FuncionarioRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FuncionarioFilterDTO filter, ExecucaoQuery<Funcionario> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

