package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.TributoAliquotaFilterDTO;
import com.stfn2.ggas.domain.TributoAliquota;

import jakarta.persistence.EntityManager;

public class TributoAliquotaRepositoryImpl extends IRepository<TributoAliquota, TributoAliquotaFilterDTO> {

	public TributoAliquotaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(TributoAliquotaFilterDTO filter, ExecucaoQuery<TributoAliquota> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

