package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturaMotivoRevisaoFilterDTO;
import com.stfn2.ggas.domain.FaturaMotivoRevisao;
import jakarta.persistence.EntityManager;

public class FaturaMotivoRevisaoRepositoryImpl extends IRepository<FaturaMotivoRevisao, FaturaMotivoRevisaoFilterDTO> {

	public FaturaMotivoRevisaoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturaMotivoRevisaoFilterDTO filter, ExecucaoQuery<FaturaMotivoRevisao> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}