package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.ImovelRamoAtividadeFilterDTO;
import com.stfn2.ggas.domain.ImovelRamoAtividade;
import jakarta.persistence.EntityManager;

public class ImovelRamoAtividadeRepositoryImpl extends IRepository<ImovelRamoAtividade, ImovelRamoAtividadeFilterDTO> {

	public ImovelRamoAtividadeRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(ImovelRamoAtividadeFilterDTO filter, ExecucaoQuery<ImovelRamoAtividade> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}