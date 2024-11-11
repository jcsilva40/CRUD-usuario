package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.LogCampanhaDescontoFilterDTO;
import com.stfn2.ggas.domain.LogCampanhaDesconto;
import jakarta.persistence.EntityManager;

public class LogCampanhaDescontoRepositoryImpl extends IRepository<LogCampanhaDesconto, LogCampanhaDescontoFilterDTO> {

	public LogCampanhaDescontoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(LogCampanhaDescontoFilterDTO filter, ExecucaoQuery<LogCampanhaDesconto> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
