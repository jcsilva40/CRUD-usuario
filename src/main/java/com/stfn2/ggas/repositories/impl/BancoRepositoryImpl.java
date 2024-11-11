package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.BancoFilterDTO;
import com.stfn2.ggas.domain.Banco;
import jakarta.persistence.EntityManager;

public class BancoRepositoryImpl extends IRepository<Banco, BancoFilterDTO> {

	public BancoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(BancoFilterDTO filter, ExecucaoQuery<Banco> execute) {
		 addFilterLike(execute, "nome", filter.getNome());
	}
}