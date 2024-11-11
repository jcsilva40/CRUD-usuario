package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoTributoAliquotaFilterDTO;
import com.stfn2.ggas.domain.PontoConsumoTributoAliquota;

import jakarta.persistence.EntityManager;

public class PontoConsumoTributoAliquotaRepositoryImpl extends IRepository<PontoConsumoTributoAliquota, PontoConsumoTributoAliquotaFilterDTO> {

	public PontoConsumoTributoAliquotaRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(PontoConsumoTributoAliquotaFilterDTO filter, ExecucaoQuery<PontoConsumoTributoAliquota> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}

