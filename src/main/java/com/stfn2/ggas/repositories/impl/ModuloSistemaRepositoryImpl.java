package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ModuloSistemaFilterDTO;
import com.stfn2.ggas.domain.ModuloSistema;
import jakarta.persistence.EntityManager;

public class ModuloSistemaRepositoryImpl extends IRepository<ModuloSistema, ModuloSistemaFilterDTO> {

	public ModuloSistemaRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(ModuloSistemaFilterDTO filter, ExecucaoQuery<ModuloSistema> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}