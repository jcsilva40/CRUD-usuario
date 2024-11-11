package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ClienteImovelFilterDTO;
import com.stfn2.ggas.domain.ClienteImovel;
import jakarta.persistence.EntityManager;

public class ClienteImovelRepositoryImpl extends IRepository<ClienteImovel, ClienteImovelFilterDTO> {

	public ClienteImovelRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ClienteImovelFilterDTO filter, ExecucaoQuery<ClienteImovel> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}