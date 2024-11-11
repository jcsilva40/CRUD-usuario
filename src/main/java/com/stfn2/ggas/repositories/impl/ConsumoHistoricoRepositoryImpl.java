package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ConsumoHistoricoFilterDTO;
import com.stfn2.ggas.domain.ConsumoHistorico;
import jakarta.persistence.EntityManager;

public class ConsumoHistoricoRepositoryImpl extends IRepository<ConsumoHistorico, ConsumoHistoricoFilterDTO> {

	public ConsumoHistoricoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ConsumoHistoricoFilterDTO filter, ExecucaoQuery<ConsumoHistorico> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
