package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.TemperaturaTrabalhoFaixaFilterDTO;
import com.stfn2.ggas.domain.TemperaturaTrabalhoFaixa;
import jakarta.persistence.EntityManager;

public class TemperaturaTrabalhoFaixaRepositoryImpl extends IRepository<TemperaturaTrabalhoFaixa, TemperaturaTrabalhoFaixaFilterDTO> {

	public TemperaturaTrabalhoFaixaRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(TemperaturaTrabalhoFaixaFilterDTO filter, ExecucaoQuery<TemperaturaTrabalhoFaixa> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}