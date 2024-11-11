package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.CampanhaDescontoVincularFilterDTO;
import com.stfn2.ggas.domain.CampanhaDescontoVincular;
import jakarta.persistence.EntityManager;

public class CampanhaDescontoVincularRepositoryImpl extends IRepository<CampanhaDescontoVincular, CampanhaDescontoVincularFilterDTO> {

	public CampanhaDescontoVincularRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(CampanhaDescontoVincularFilterDTO filter, ExecucaoQuery<CampanhaDescontoVincular> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
