package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.SegmentoPaiFilterDTO;
import com.stfn2.ggas.domain.SegmentoPai;
import jakarta.persistence.EntityManager;

public class SegmentoPaiRepositoryImpl extends IRepository<SegmentoPai, SegmentoPaiFilterDTO> {

	public SegmentoPaiRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(SegmentoPaiFilterDTO filter, ExecucaoQuery<SegmentoPai> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
