package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturaMensagemFilterDTO;
import com.stfn2.ggas.domain.FaturaMensagem;
import jakarta.persistence.EntityManager;

public class FaturaMensagemRepositoryImpl extends IRepository<FaturaMensagem, FaturaMensagemFilterDTO> {

	public FaturaMensagemRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(FaturaMensagemFilterDTO filter, ExecucaoQuery<FaturaMensagem> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
