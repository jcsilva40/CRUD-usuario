package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoARealizarFilterDTO;
import com.stfn2.ggas.domain.CreditoDebitoARealizar;
import jakarta.persistence.EntityManager;

public class CreditoDebitoARealizarRepositoryImpl extends IRepository<CreditoDebitoARealizar, CreditoDebitoARealizarFilterDTO> {

	public CreditoDebitoARealizarRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(CreditoDebitoARealizarFilterDTO filter, ExecucaoQuery<CreditoDebitoARealizar> execute) {

		addFilterPositive(execute, "anoMesFaturamento", filter.getAnoMesFaturamento());
		addFilterPositive(execute, "ciclo", filter.getCiclo());
		addFilter(execute, "creditoDebitoSituacao", filter.getCreditoDebitoSituacao());
		addFilterSubAttribute2(execute, "creditoDebitoNegociado", "pontoConsumo", "cil",  filter.getPontoConsumoCil());
		addFilterSubAttribute(execute, "creditoDebitoNegociado", "descricao", filter.getCredDebNegComplemento());

	}
}