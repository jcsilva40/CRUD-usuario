package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ConsumoAnormalidadeFilterDTO;
import com.stfn2.ggas.domain.ConsumoAnormalidade;
import jakarta.persistence.EntityManager;

public class ConsumoAnormalidadeRepositoryImpl extends IRepository<ConsumoAnormalidade, ConsumoAnormalidadeFilterDTO> {

	public ConsumoAnormalidadeRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ConsumoAnormalidadeFilterDTO filter, ExecucaoQuery<ConsumoAnormalidade> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
