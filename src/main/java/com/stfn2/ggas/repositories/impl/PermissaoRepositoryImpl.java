package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.PermissaoFilterDTO;
import com.stfn2.ggas.domain.Permissao;
import jakarta.persistence.EntityManager;

public class PermissaoRepositoryImpl extends IRepository<Permissao, PermissaoFilterDTO> {

	public PermissaoRepositoryImpl(EntityManager em) {
		super(em, "descricao");
	}

	@Override
	protected void filters(PermissaoFilterDTO filter, ExecucaoQuery<Permissao> execute) {
	}
}
