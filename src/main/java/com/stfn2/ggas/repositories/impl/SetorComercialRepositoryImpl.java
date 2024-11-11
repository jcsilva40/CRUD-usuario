package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.SetorComercialFilterDTO;
import com.stfn2.ggas.domain.SetorComercial;
import jakarta.persistence.EntityManager;

public class SetorComercialRepositoryImpl extends IRepository<SetorComercial, SetorComercialFilterDTO> {

	public SetorComercialRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(SetorComercialFilterDTO filter, ExecucaoQuery<SetorComercial> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
