package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaDescontoFilterDTO;
import com.stfn2.ggas.domain.TarifaVigenciaDesconto;

import jakarta.persistence.EntityManager;

public class TarifaVigenciaDescontoRepositoryImpl extends IRepository<TarifaVigenciaDesconto, TarifaVigenciaDescontoFilterDTO> {

	public TarifaVigenciaDescontoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(TarifaVigenciaDescontoFilterDTO filter, ExecucaoQuery<TarifaVigenciaDesconto> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

