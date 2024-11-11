package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.RotaFilterDTO;
import com.stfn2.ggas.domain.Rota;

import jakarta.persistence.EntityManager;

public class RotaRepositoryImpl extends IRepository<Rota, RotaFilterDTO> {

	public RotaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(RotaFilterDTO filter, ExecucaoQuery<Rota> execute) {
		 //addFilterSubAttribute(execute, "faturamentoGrupo", "descricao", filter.getFaturamentoGrupoDescricao());
		 //addFilterSubAttribute(execute, "periodicidade", "descricao", filter.getPeriodicidadeDescricao());

	}
}

