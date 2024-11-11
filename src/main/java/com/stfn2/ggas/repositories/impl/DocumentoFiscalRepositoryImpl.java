package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DocumentoFiscalFilterDTO;
import com.stfn2.ggas.domain.DocumentoFiscal;
import jakarta.persistence.EntityManager;

public class DocumentoFiscalRepositoryImpl extends IRepository<DocumentoFiscal, DocumentoFiscalFilterDTO> {

	public DocumentoFiscalRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(DocumentoFiscalFilterDTO filter, ExecucaoQuery<DocumentoFiscal> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}
