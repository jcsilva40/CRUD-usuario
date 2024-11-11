package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.MedidorMarcaFilterDTO;
import com.stfn2.ggas.domain.MedidorMarca;
import jakarta.persistence.EntityManager;

public class MedidorMarcaRepositoryImpl extends IRepository<MedidorMarca, MedidorMarcaFilterDTO> {

	public MedidorMarcaRepositoryImpl(EntityManager em) {
		super(em, "descricao");
	}

	@Override
	protected void filters(MedidorMarcaFilterDTO filter, ExecucaoQuery<MedidorMarca> execute) {
		 //addFilterLike(execute, "descricao", filter.getDescricao());
	}
}