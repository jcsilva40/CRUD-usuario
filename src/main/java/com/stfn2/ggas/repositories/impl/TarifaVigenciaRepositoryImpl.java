package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaFilterDTO;
import com.stfn2.ggas.domain.TarifaVigencia;

import jakarta.persistence.EntityManager;

public class TarifaVigenciaRepositoryImpl extends IRepository<TarifaVigencia, TarifaVigenciaFilterDTO> {

	public TarifaVigenciaRepositoryImpl(EntityManager em) {
		super(em, "dataVigencia");
	}

	@Override
	protected void filters(TarifaVigenciaFilterDTO filter, ExecucaoQuery<TarifaVigencia> execute) {
		addFilterPositiveFk(execute, "tarifa", filter.getIdTarifa());
	}
}

