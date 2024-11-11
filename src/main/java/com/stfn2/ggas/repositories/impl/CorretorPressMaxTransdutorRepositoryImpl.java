package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.CorretorPressMaxTransdutorFilterDTO;
import com.stfn2.ggas.domain.CorretorPressMaxTransdutor;
import jakarta.persistence.EntityManager;

public class CorretorPressMaxTransdutorRepositoryImpl extends IRepository<CorretorPressMaxTransdutor, CorretorPressMaxTransdutorFilterDTO> {

	public CorretorPressMaxTransdutorRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(CorretorPressMaxTransdutorFilterDTO filter, ExecucaoQuery<CorretorPressMaxTransdutor> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}