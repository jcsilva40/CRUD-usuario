package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.MedidorModeloFilterDTO;
import com.stfn2.ggas.domain.MedidorModelo;
import jakarta.persistence.EntityManager;

public class MedidorModeloRepositoryImpl extends IRepository<MedidorModelo, MedidorModeloFilterDTO> {

	public MedidorModeloRepositoryImpl(EntityManager em) {
		super(em, "descricao");
	}

	@Override
	protected void filters(MedidorModeloFilterDTO filter, ExecucaoQuery<MedidorModelo> execute) {
		 //addFilterLike(execute, "descricao", filter.getDescricao());
	}
}