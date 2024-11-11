package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaFaixaFilterDTO;
import com.stfn2.ggas.domain.TarifaVigenciaFaixa;

import jakarta.persistence.EntityManager;

public class TarifaVigenciaFaixaRepositoryImpl extends IRepository<TarifaVigenciaFaixa, TarifaVigenciaFaixaFilterDTO> {

	public TarifaVigenciaFaixaRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(TarifaVigenciaFaixaFilterDTO filter, ExecucaoQuery<TarifaVigenciaFaixa> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

