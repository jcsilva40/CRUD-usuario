package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.HistoricoLegadoFilterDTO;
import com.stfn2.ggas.domain.HistoricoLegado;
import jakarta.persistence.EntityManager;

public class HistoricoLegadoRepositoryImpl extends IRepository<HistoricoLegado, HistoricoLegadoFilterDTO> {

	public HistoricoLegadoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(HistoricoLegadoFilterDTO filter, ExecucaoQuery<HistoricoLegado> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
