package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.NacionalidadeFilterDTO;
import com.stfn2.ggas.domain.Nacionalidade;
import jakarta.persistence.EntityManager;

public class NacionalidadeRepositoryImpl extends IRepository<Nacionalidade, NacionalidadeFilterDTO> {

	public NacionalidadeRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(NacionalidadeFilterDTO filter, ExecucaoQuery<Nacionalidade> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}