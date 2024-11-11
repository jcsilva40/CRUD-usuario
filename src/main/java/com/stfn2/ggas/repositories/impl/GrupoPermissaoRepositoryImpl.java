package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.GrupoPermissaoFilterDTO;
import com.stfn2.ggas.domain.GrupoPermissao;
import jakarta.persistence.EntityManager;

public class GrupoPermissaoRepositoryImpl extends IRepository<GrupoPermissao, GrupoPermissaoFilterDTO> {

	public GrupoPermissaoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(GrupoPermissaoFilterDTO filter, ExecucaoQuery<GrupoPermissao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
