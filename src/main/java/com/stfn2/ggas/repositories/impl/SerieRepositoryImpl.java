package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.SerieFilterDTO;
import com.stfn2.ggas.domain.Serie;
import jakarta.persistence.EntityManager;

public class SerieRepositoryImpl extends IRepository<Serie, SerieFilterDTO> {

	public SerieRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(SerieFilterDTO filter, ExecucaoQuery<Serie> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
