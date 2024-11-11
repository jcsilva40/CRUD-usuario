package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutFilterDTO;
import com.stfn2.ggas.domain.DocumentoLayout;
import jakarta.persistence.EntityManager;

public class DocumentoLayoutRepositoryImpl extends IRepository<DocumentoLayout, DocumentoLayoutFilterDTO> {

	public DocumentoLayoutRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(DocumentoLayoutFilterDTO filter, ExecucaoQuery<DocumentoLayout> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}