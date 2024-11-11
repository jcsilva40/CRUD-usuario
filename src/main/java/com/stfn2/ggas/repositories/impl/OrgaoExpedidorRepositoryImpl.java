package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.OrgaoExpedidorFilterDTO;
import com.stfn2.ggas.domain.OrgaoExpedidor;
import jakarta.persistence.EntityManager;

public class OrgaoExpedidorRepositoryImpl extends IRepository<OrgaoExpedidor, OrgaoExpedidorFilterDTO> {

	public OrgaoExpedidorRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(OrgaoExpedidorFilterDTO filter, ExecucaoQuery<OrgaoExpedidor> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}