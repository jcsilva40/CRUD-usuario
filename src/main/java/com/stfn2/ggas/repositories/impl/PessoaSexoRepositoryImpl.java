package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.PessoaSexoFilterDTO;
import com.stfn2.ggas.domain.PessoaSexo;
import jakarta.persistence.EntityManager;

public class PessoaSexoRepositoryImpl extends IRepository<PessoaSexo, PessoaSexoFilterDTO> {

	public PessoaSexoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(PessoaSexoFilterDTO filter, ExecucaoQuery<PessoaSexo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}