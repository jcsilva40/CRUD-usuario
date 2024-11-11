package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoGrupoFilterDTO;
import com.stfn2.ggas.domain.FaturamentoGrupo;

import jakarta.persistence.EntityManager;

public class FaturamentoGrupoRepositoryImpl extends IRepository<FaturamentoGrupo, FaturamentoGrupoFilterDTO> {

	public FaturamentoGrupoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(FaturamentoGrupoFilterDTO filter, ExecucaoQuery<FaturamentoGrupo> execute) {
		 addFilterPositive(execute, "id", filter.getId());
	}
}

