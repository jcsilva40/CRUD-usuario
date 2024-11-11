package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FeriadoFilterDTO;
import com.stfn2.ggas.domain.Feriado;
import jakarta.persistence.EntityManager;

public class FeriadoRepositoryImpl extends IRepository<Feriado, FeriadoFilterDTO> {

	public FeriadoRepositoryImpl(EntityManager em) {
		super(em, "dataFeriado");
	}

	@Override
	protected void filters(FeriadoFilterDTO filter, ExecucaoQuery<Feriado> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
            addFilterMonth(execute, "dataFeriado", filter.getMes());
            addFilterYear(execute, "dataFeriado", filter.getAno());
	}
}