package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DebitoAutomaticoFilterDTO;
import com.stfn2.ggas.domain.DebitoAutomatico;
import jakarta.persistence.EntityManager;

public class DebitoAutomaticoRepositoryImpl extends IRepository<DebitoAutomatico, DebitoAutomaticoFilterDTO> {

	public DebitoAutomaticoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(DebitoAutomaticoFilterDTO filter, ExecucaoQuery<DebitoAutomatico> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
