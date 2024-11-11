package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.CorretorTempMaxTransdutorFilterDTO;
import com.stfn2.ggas.domain.CorretorTempMaxTransdutor;
import jakarta.persistence.EntityManager;

public class CorretorTempMaxTransdutorRepositoryImpl extends IRepository<CorretorTempMaxTransdutor, CorretorTempMaxTransdutorFilterDTO> {

	public CorretorTempMaxTransdutorRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(CorretorTempMaxTransdutorFilterDTO filter, ExecucaoQuery<CorretorTempMaxTransdutor> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}