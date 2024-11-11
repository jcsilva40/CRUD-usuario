package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.MedidorCapacidadeFilterDTO;
import com.stfn2.ggas.domain.MedidorCapacidade;
import jakarta.persistence.EntityManager;

public class MedidorCapacidadeRepositoryImpl extends IRepository<MedidorCapacidade, MedidorCapacidadeFilterDTO> {

	public MedidorCapacidadeRepositoryImpl(EntityManager em) {
		super(em, "descricao");
	}

	@Override
	protected void filters(MedidorCapacidadeFilterDTO filter, ExecucaoQuery<MedidorCapacidade> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}