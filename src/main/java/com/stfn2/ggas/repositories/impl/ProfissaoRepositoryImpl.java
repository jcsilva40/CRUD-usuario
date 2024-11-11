package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.Profissao;
import com.stfn2.ggas.domain.dtos.filter.ProfissaoFilterDTO;
import jakarta.persistence.EntityManager;

public class ProfissaoRepositoryImpl extends IRepository<Profissao, ProfissaoFilterDTO> {

	public ProfissaoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ProfissaoFilterDTO profissaoFilterDTO, ExecucaoQuery<Profissao> execute) {

	}
}