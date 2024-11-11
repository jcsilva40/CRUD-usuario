package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoAtividadeCronogrFilterDTO;
import com.stfn2.ggas.domain.FaturamentoAtividadeCronograma;
import jakarta.persistence.EntityManager;

public class FaturamentoAtividadeCronogrRepositoryImpl extends IRepository<FaturamentoAtividadeCronograma, FaturamentoAtividadeCronogrFilterDTO> {

	public FaturamentoAtividadeCronogrRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturamentoAtividadeCronogrFilterDTO filter, ExecucaoQuery<FaturamentoAtividadeCronograma> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}