package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoAnomaliaHistoricoFilterDTO;
import com.stfn2.ggas.domain.FaturamentoAnomaliaHistorico;
import jakarta.persistence.EntityManager;

public class FaturamentoAnomaliaHistoricoRepositoryImpl extends IRepository<FaturamentoAnomaliaHistorico, FaturamentoAnomaliaHistoricoFilterDTO> {

	public FaturamentoAnomaliaHistoricoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturamentoAnomaliaHistoricoFilterDTO filter, ExecucaoQuery<FaturamentoAnomaliaHistorico> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}