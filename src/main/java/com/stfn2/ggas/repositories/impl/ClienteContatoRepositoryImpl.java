package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.ClienteContato;
import com.stfn2.ggas.domain.dtos.filter.ClienteContatoFilterDTO;
import jakarta.persistence.EntityManager;

public class ClienteContatoRepositoryImpl extends IRepository<ClienteContato, ClienteContatoFilterDTO> {

	public ClienteContatoRepositoryImpl(EntityManager em) {
		super(em, "nome");
	}

	@Override
	protected void filters(ClienteContatoFilterDTO filter, ExecucaoQuery<ClienteContato> execute) {
		addFilterSubAttribute(execute, "cliente","id", filter.getClienteId());
	}
}

