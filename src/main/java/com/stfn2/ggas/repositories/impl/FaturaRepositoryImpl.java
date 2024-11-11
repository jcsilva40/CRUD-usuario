package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturaFilterDTO;
import com.stfn2.ggas.domain.Fatura;

import jakarta.persistence.EntityManager;

public class FaturaRepositoryImpl extends IRepository<Fatura, FaturaFilterDTO> {

	public FaturaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(FaturaFilterDTO filter, ExecucaoQuery<Fatura> execute) {
		addFilterSubAttribute(execute, "rota", "id", filter.getRotaId());
		addFilterSubAttribute(execute, "segmento", "id", filter.getSegmentoId());
		addFilterSubAttribute(execute, "pontoConsumo", "id", filter.getPontoConsumoId());
		addFilterPositive(execute, "anoMes", filter.getAnoMes());
		addFilterPositive(execute, "ciclo", filter.getCiclo());
	}
}

