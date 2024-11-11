package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.TarifaFaixaDescontoFilterDTO;
import com.stfn2.ggas.domain.TarifaFaixaDesconto;

import jakarta.persistence.EntityManager;

public class TarifaFaixaDescontoRepositoryImpl extends IRepository<TarifaFaixaDesconto, TarifaFaixaDescontoFilterDTO> {

	public TarifaFaixaDescontoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(TarifaFaixaDescontoFilterDTO filter, ExecucaoQuery<TarifaFaixaDesconto> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

