package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.MedidorFilterDTO;
import com.stfn2.ggas.domain.Medidor;
import jakarta.persistence.EntityManager;

public class MedidorRepositoryImpl extends IRepository<Medidor, MedidorFilterDTO> {

	public MedidorRepositoryImpl(EntityManager em) {
		super(em, "descricao");
	}

	@Override
	protected void filters(MedidorFilterDTO filter, ExecucaoQuery<Medidor> execute) {
		addFilterLike(execute, "descricao", filter.getDescricao());
	}
}