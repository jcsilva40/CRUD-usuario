package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.CepFilterDTO;
import com.stfn2.ggas.domain.Cep;

import jakarta.persistence.EntityManager;

public class CepRepositoryImpl extends IRepository<Cep, CepFilterDTO> {

	public CepRepositoryImpl(EntityManager em) {
		super(em, "numeroCep");
	}

	@Override
	protected void filters(CepFilterDTO cepFilterDTO, ExecucaoQuery<Cep> execute) {
		addFilter(execute, "numeroCep", filter.getNumeroCep());
		addFilter(execute, "siglaUf", filter.getSiglaUf());
		addFilterLike(execute, "nomeMunicipio", filter.getNomeMunicipio());
		addFilterLike(execute, "bairro", filter.getBairro());
		addFilterLike(execute, "logradouro", filter.getLogradouro());
	}
}

