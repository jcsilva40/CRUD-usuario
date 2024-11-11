package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ImovelContatoFilterDTO;
import com.stfn2.ggas.domain.ImovelContato;
import jakarta.persistence.EntityManager;

public class ImovelContatoRepositoryImpl extends IRepository<ImovelContato, ImovelContatoFilterDTO> {

	public ImovelContatoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ImovelContatoFilterDTO filter, ExecucaoQuery<ImovelContato> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}