package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.MedidorMotivoOperacaoFilterDTO;
import com.stfn2.ggas.domain.MedidorMotivoOperacao;
import jakarta.persistence.EntityManager;

public class MedidorMotivoOperacaoRepositoryImpl extends IRepository<MedidorMotivoOperacao, MedidorMotivoOperacaoFilterDTO> {

	public MedidorMotivoOperacaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(MedidorMotivoOperacaoFilterDTO filter, ExecucaoQuery<MedidorMotivoOperacao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}