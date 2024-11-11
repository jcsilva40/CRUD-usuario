package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.Empresa;
import com.stfn2.ggas.domain.dtos.filter.EmpresaFilterDTO;
import jakarta.persistence.EntityManager;

public class EmpresaRepositoryImpl extends IRepository<Empresa, EmpresaFilterDTO> {

	public EmpresaRepositoryImpl(EntityManager em) {
		super(em, "nome");
	}

	@Override
	protected void filters(EmpresaFilterDTO filter, ExecucaoQuery<Empresa> execute) {
		//addFilterLike(execute, "nome", filter.getNome());
	}
}

