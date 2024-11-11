package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.RecursoPermissaoFilterDTO;
import com.stfn2.ggas.domain.RecursoPermissao;
import jakarta.persistence.EntityManager;

public class RecursoPermissaoRepositoryImpl extends IRepository<RecursoPermissao, RecursoPermissaoFilterDTO> {

	public RecursoPermissaoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(RecursoPermissaoFilterDTO filter, ExecucaoQuery<RecursoPermissao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
