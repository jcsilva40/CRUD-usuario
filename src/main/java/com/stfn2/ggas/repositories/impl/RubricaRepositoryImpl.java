package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.RubricaFilterDTO;
import com.stfn2.ggas.domain.Rubrica;

import jakarta.persistence.EntityManager;

public class RubricaRepositoryImpl extends IRepository<Rubrica, RubricaFilterDTO> {

	public RubricaRepositoryImpl(EntityManager em){
		super(em, "id");
	}

	@Override
	protected void filters(RubricaFilterDTO filter, ExecucaoQuery<Rubrica> execute) {
		addFilterLike(execute, "descricao", filter.getDescricao());
		addFilterLike(execute, "descricaoImpressao", filter.getDescricaoImpressao());
		addFilterSubAttribute(execute, "financiamentoTipo", "descricao", filter.getFinanciamentoTipoDescricao());
		addFilterSubAttribute(execute, "financiamentoTipo", "id", filter.getFinanciamentoTipoId());

	}
}

