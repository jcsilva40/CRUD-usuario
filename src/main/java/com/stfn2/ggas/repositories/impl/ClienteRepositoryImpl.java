package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.Cliente;
import com.stfn2.ggas.domain.dtos.filter.ClienteFilterDTO;
import jakarta.persistence.EntityManager;

public class ClienteRepositoryImpl extends IRepository<Cliente, ClienteFilterDTO> {

	public ClienteRepositoryImpl(EntityManager em) {
		super(em, "nome");
	}

	@Override
	protected void filters(ClienteFilterDTO filter, ExecucaoQuery<Cliente> execute) {
		addFilterLike(execute, "nome", filter.getNome());
	}
}

