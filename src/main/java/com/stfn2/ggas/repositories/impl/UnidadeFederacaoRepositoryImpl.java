package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.UnidadeFederacaoFilterDTO;
import com.stfn2.ggas.domain.UnidadeFederacao;

import jakarta.persistence.EntityManager;

public class UnidadeFederacaoRepositoryImpl extends IRepository<UnidadeFederacao, UnidadeFederacaoFilterDTO> {

	public UnidadeFederacaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(UnidadeFederacaoFilterDTO filter, ExecucaoQuery<UnidadeFederacao> execute) {
		 addFilterLike(execute, "sigla", filter.getSigla());
	}
}

