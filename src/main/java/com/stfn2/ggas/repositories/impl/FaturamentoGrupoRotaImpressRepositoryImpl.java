package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoGrupoRotaImpressFilterDTO;
import com.stfn2.ggas.domain.FaturamentoGrupoRotaImpress;
import jakarta.persistence.EntityManager;

public class FaturamentoGrupoRotaImpressRepositoryImpl extends IRepository<FaturamentoGrupoRotaImpress, FaturamentoGrupoRotaImpressFilterDTO> {

	public FaturamentoGrupoRotaImpressRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(FaturamentoGrupoRotaImpressFilterDTO filter, ExecucaoQuery<FaturamentoGrupoRotaImpress> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}