package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaTributoFilterDTO;
import com.stfn2.ggas.domain.TarifaVigenciaTributo;

import jakarta.persistence.EntityManager;

public class TarifaVigenciaTributoRepositoryImpl extends IRepository<TarifaVigenciaTributo, TarifaVigenciaTributoFilterDTO> {

	public TarifaVigenciaTributoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(TarifaVigenciaTributoFilterDTO filter, ExecucaoQuery<TarifaVigenciaTributo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

