package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.OperacaoSistemaFilterDTO;
import com.stfn2.ggas.domain.OperacaoSistema;
import jakarta.persistence.EntityManager;

public class OperacaoSistemaRepositoryImpl extends IRepository<OperacaoSistema, OperacaoSistemaFilterDTO> {

	public OperacaoSistemaRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(OperacaoSistemaFilterDTO filter, ExecucaoQuery<OperacaoSistema> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}