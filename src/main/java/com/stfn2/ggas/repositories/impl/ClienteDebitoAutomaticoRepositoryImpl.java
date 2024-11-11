package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ClienteDebitoAutomaticoFilterDTO;
import com.stfn2.ggas.domain.ClienteDebitoAutomatico;
import jakarta.persistence.EntityManager;

public class ClienteDebitoAutomaticoRepositoryImpl extends IRepository<ClienteDebitoAutomatico, ClienteDebitoAutomaticoFilterDTO> {

	public ClienteDebitoAutomaticoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ClienteDebitoAutomaticoFilterDTO filter, ExecucaoQuery<ClienteDebitoAutomatico> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
