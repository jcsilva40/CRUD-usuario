package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.MedidorInstalacao;
import com.stfn2.ggas.domain.dtos.filter.MedidorInstalacaoFilterDTO;
import jakarta.persistence.EntityManager;

public class MedidorInstalacaoRepositoryImpl extends IRepository<MedidorInstalacao, MedidorInstalacaoFilterDTO> {

	public MedidorInstalacaoRepositoryImpl(EntityManager em) {
		super(em, "pontoConsumo");
	}

	@Override
	protected void filters(MedidorInstalacaoFilterDTO filter, ExecucaoQuery<MedidorInstalacao> execute) {

		addFilterSubAttribute(execute, "pontoConsumo", "cil", filter.getPontoConsumoCil());
		addFilterSubAttribute(execute, "medidor", "descricao", filter.getMedidorDescricao());
	}
}