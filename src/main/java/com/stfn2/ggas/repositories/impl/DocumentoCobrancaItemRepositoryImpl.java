package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DocumentoCobrancaItemFilterDTO;
import com.stfn2.ggas.domain.DocumentoCobrancaItem;
import jakarta.persistence.EntityManager;

public class DocumentoCobrancaItemRepositoryImpl extends IRepository<DocumentoCobrancaItem, DocumentoCobrancaItemFilterDTO> {

	public DocumentoCobrancaItemRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(DocumentoCobrancaItemFilterDTO filter, ExecucaoQuery<DocumentoCobrancaItem> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
