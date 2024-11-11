package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.EntidadeClasse;
import com.stfn2.ggas.domain.dtos.filter.EntidadeClasseFilterDTO;
import jakarta.persistence.EntityManager;

public class EntidadeClasseRepositoryImpl extends IRepository<EntidadeClasse , EntidadeClasseFilterDTO> {

	public EntidadeClasseRepositoryImpl(EntityManager em) {
		super(em, "descricao");
	}


	@Override
	protected void filters(EntidadeClasseFilterDTO filter, ExecucaoQuery<EntidadeClasse> execute) {
		addFilterLike(execute, "abreviado", filter.getAbreviado());
	}

}
