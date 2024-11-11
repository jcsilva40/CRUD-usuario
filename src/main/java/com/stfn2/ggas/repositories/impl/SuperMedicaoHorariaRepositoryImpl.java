package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.SuperMedicaoDiaria;
import com.stfn2.ggas.domain.SuperMedicaoHoraria;
import com.stfn2.ggas.domain.dtos.filter.SuperMedicaoDiariaFilterDTO;
import com.stfn2.ggas.domain.dtos.filter.SuperMedicaoHorariaFilterDTO;
import jakarta.persistence.EntityManager;

public class SuperMedicaoHorariaRepositoryImpl extends IRepository<SuperMedicaoHoraria, SuperMedicaoHorariaFilterDTO> {

	public SuperMedicaoHorariaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(SuperMedicaoHorariaFilterDTO filter, ExecucaoQuery<SuperMedicaoHoraria> execute) {
	}
}