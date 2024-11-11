package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutRegistroFilterDTO;
import com.stfn2.ggas.domain.DocumentoLayoutRegistro;
import jakarta.persistence.EntityManager;

public class DocumentoLayoutRegistroRepositoryImpl extends IRepository<DocumentoLayoutRegistro, DocumentoLayoutRegistroFilterDTO> {

	public DocumentoLayoutRegistroRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(DocumentoLayoutRegistroFilterDTO filter, ExecucaoQuery<DocumentoLayoutRegistro> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}