package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.CampanhaDescontoFilterDTO;
import com.stfn2.ggas.domain.CampanhaDesconto;
import jakarta.persistence.EntityManager;

public class CampanhaDescontoRepositoryImpl extends IRepository<CampanhaDesconto, CampanhaDescontoFilterDTO> {

	public CampanhaDescontoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(CampanhaDescontoFilterDTO filter, ExecucaoQuery<CampanhaDesconto> execute) {		
	}
}
