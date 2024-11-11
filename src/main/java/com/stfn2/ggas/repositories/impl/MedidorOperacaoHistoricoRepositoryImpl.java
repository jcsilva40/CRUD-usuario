package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.MedidorOperacaoHistoricoFilterDTO;
import com.stfn2.ggas.domain.MedidorOperacaoHistorico;
import jakarta.persistence.EntityManager;

public class MedidorOperacaoHistoricoRepositoryImpl extends IRepository<MedidorOperacaoHistorico, MedidorOperacaoHistoricoFilterDTO> {

	public MedidorOperacaoHistoricoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(MedidorOperacaoHistoricoFilterDTO filter, ExecucaoQuery<MedidorOperacaoHistorico> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}