package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DocumentoCobrancaFilterDTO;
import com.stfn2.ggas.domain.DocumentoCobranca;
import jakarta.persistence.EntityManager;

public class DocumentoCobrancaRepositoryImpl extends IRepository<DocumentoCobranca, DocumentoCobrancaFilterDTO> {

	public DocumentoCobrancaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(DocumentoCobrancaFilterDTO filter, ExecucaoQuery<DocumentoCobranca> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
