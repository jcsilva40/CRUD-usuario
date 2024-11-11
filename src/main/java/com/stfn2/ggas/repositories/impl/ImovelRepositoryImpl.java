package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ImovelFilterDTO;
import com.stfn2.ggas.domain.Imovel;
import jakarta.persistence.EntityManager;

public class ImovelRepositoryImpl extends IRepository<Imovel, ImovelFilterDTO> {

	public ImovelRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ImovelFilterDTO filter, ExecucaoQuery<Imovel> execute) {
		 addFilterPositive(execute, "nip", filter.getNip());
		 addFilter(execute, "zonaBloqueio", filter.getZonaBloqueio());
		 addFilterSubAttribute(execute, "pontoConsumos", "cil", filter.getCil());

	}
}