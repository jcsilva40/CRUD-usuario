package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.UsuarioGrupoFilterDTO;
import com.stfn2.ggas.domain.UsuarioGrupo;
import jakarta.persistence.EntityManager;

public class UsuarioGrupoRepositoryImpl extends IRepository<UsuarioGrupo, UsuarioGrupoFilterDTO> {

	public UsuarioGrupoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(UsuarioGrupoFilterDTO filter, ExecucaoQuery<UsuarioGrupo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
