package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DocumentoImpressaoLayoutFilterDTO;
import com.stfn2.ggas.domain.DocumentoImpressaoLayout;
import jakarta.persistence.EntityManager;

public class DocumentoImpressaoLayoutRepositoryImpl extends IRepository<DocumentoImpressaoLayout, DocumentoImpressaoLayoutFilterDTO> {

	public DocumentoImpressaoLayoutRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(DocumentoImpressaoLayoutFilterDTO filter, ExecucaoQuery<DocumentoImpressaoLayout> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
