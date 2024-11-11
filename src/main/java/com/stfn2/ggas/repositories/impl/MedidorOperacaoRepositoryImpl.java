package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.MedidorOperacaoFilterDTO;
import com.stfn2.ggas.domain.MedidorOperacao;
import jakarta.persistence.EntityManager;

public class MedidorOperacaoRepositoryImpl extends IRepository<MedidorOperacao, MedidorOperacaoFilterDTO> {

	public MedidorOperacaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(MedidorOperacaoFilterDTO filter, ExecucaoQuery<MedidorOperacao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}