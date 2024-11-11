package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.NaturezaOperacaoFilterDTO;
import com.stfn2.ggas.domain.NaturezaOperacao;
import jakarta.persistence.EntityManager;

public class NaturezaOperacaoRepositoryImpl extends IRepository<NaturezaOperacao, NaturezaOperacaoFilterDTO> {

	public NaturezaOperacaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(NaturezaOperacaoFilterDTO filter, ExecucaoQuery<NaturezaOperacao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}