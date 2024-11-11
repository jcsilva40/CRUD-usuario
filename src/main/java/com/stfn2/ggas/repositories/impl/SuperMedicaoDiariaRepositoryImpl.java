package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.Agencia;
import com.stfn2.ggas.domain.SuperMedicaoDiaria;
import com.stfn2.ggas.domain.dtos.filter.AgenciaFilterDTO;
import com.stfn2.ggas.domain.dtos.filter.SuperMedicaoDiariaFilterDTO;
import jakarta.persistence.EntityManager;

public class SuperMedicaoDiariaRepositoryImpl extends IRepository<SuperMedicaoDiaria, SuperMedicaoDiariaFilterDTO> {

	public SuperMedicaoDiariaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(SuperMedicaoDiariaFilterDTO filter, ExecucaoQuery<SuperMedicaoDiaria> execute) {
		addFilterPositive(execute, "anoMes", filter.getAnoMes());
		addFilterPositive(execute, "numeroCiclo", filter.getNumeroCiclo());
		addFilter(execute, "cil", filter.getCil());
	}
}