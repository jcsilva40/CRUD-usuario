package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorFilterDTO;
import com.stfn2.ggas.domain.Arrecadador;
import jakarta.persistence.EntityManager;

public class ArrecadadorRepositoryImpl extends IRepository<Arrecadador, ArrecadadorFilterDTO> {

	public ArrecadadorRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ArrecadadorFilterDTO filter, ExecucaoQuery<Arrecadador> execute) {

		//addFilterSubAttribute(execute, "cliente", "nome", filter.getClienteNome());
		//addFilterSubAttribute(execute, "banco", "nome", filter.getBancoNome());

	}
}