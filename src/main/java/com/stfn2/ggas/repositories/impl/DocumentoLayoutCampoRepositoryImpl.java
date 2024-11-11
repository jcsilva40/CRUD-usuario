package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutCampoFilterDTO;
import com.stfn2.ggas.domain.DocumentoLayoutCampo;
import jakarta.persistence.EntityManager;

public class DocumentoLayoutCampoRepositoryImpl extends IRepository<DocumentoLayoutCampo, DocumentoLayoutCampoFilterDTO> {

	public DocumentoLayoutCampoRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(DocumentoLayoutCampoFilterDTO filter, ExecucaoQuery<DocumentoLayoutCampo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}