package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.MedidorDiametroFilterDTO;
import com.stfn2.ggas.domain.MedidorDiametro;
import jakarta.persistence.EntityManager;

public class MedidorDiametroRepositoryImpl extends IRepository<MedidorDiametro, MedidorDiametroFilterDTO> {

	public MedidorDiametroRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(MedidorDiametroFilterDTO filter, ExecucaoQuery<MedidorDiametro> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}