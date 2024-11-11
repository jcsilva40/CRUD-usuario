package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtofilter.PrecoMedioPonderadoFilterDTO;
import com.stfn2.ggas.domain.PrecoMedioPonderado;
import jakarta.persistence.EntityManager;

public class PrecoMedioPonderadoRepositoryImpl extends IRepository<PrecoMedioPonderado, PrecoMedioPonderadoFilterDTO> {

	public PrecoMedioPonderadoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(PrecoMedioPonderadoFilterDTO filter, ExecucaoQuery<PrecoMedioPonderado> execute) {
		addFilterPositive(execute, "referencia", filter.getReferencia());
	}
}