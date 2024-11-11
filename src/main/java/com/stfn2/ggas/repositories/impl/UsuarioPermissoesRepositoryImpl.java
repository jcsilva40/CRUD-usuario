package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.UsuarioPermissoesFilterDTO;
import com.stfn2.ggas.domain.UsuarioPermissoes;
import jakarta.persistence.EntityManager;

public class UsuarioPermissoesRepositoryImpl extends IRepository<UsuarioPermissoes, UsuarioPermissoesFilterDTO> {

	public UsuarioPermissoesRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(UsuarioPermissoesFilterDTO filter, ExecucaoQuery<UsuarioPermissoes> execute) {
		addFilterPositive(execute, "permissao", filter.getPermissaoId());
		addFilterPositive(execute, "usuario", filter.getUserId());
	}
}
