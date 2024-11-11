package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.MedicaoHistoricoFilterDTO;
import com.stfn2.ggas.domain.MedicaoHistorico;
import jakarta.persistence.EntityManager;

public class MedicaoHistoricoRepositoryImpl extends IRepository<MedicaoHistorico, MedicaoHistoricoFilterDTO> {

	public MedicaoHistoricoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(MedicaoHistoricoFilterDTO filter, ExecucaoQuery<MedicaoHistorico> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
		addFilterSubAttribute(execute, "pontoConsumo", "descricao", filter.getPontoConsumoDescricao());
		addFilter(execute, "anoMes", filter.getAnoMes());
	}
}
