package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.TipoContatoFilterDTO;
import com.stfn2.ggas.domain.TipoContato;
import jakarta.persistence.EntityManager;

public class TipoContatoRepositoryImpl extends IRepository<TipoContato, TipoContatoFilterDTO> {

	public TipoContatoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(TipoContatoFilterDTO filter, ExecucaoQuery<TipoContato> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}