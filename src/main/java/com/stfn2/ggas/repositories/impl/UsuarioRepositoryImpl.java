package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.config.security.domain.dto.UsuarioFilterDTO;
import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import jakarta.persistence.EntityManager;

public class UsuarioRepositoryImpl extends IRepository<User, UsuarioFilterDTO> {

	public UsuarioRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(UsuarioFilterDTO usuarioFilterDTO, ExecucaoQuery<User> execute) {

	}
}