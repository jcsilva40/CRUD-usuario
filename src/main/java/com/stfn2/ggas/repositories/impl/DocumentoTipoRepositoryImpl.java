package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DocumentoTipoFilterDTO;
import com.stfn2.ggas.domain.DocumentoTipo;
import jakarta.persistence.EntityManager;

public class DocumentoTipoRepositoryImpl extends IRepository<DocumentoTipo, DocumentoTipoFilterDTO> {

	public DocumentoTipoRepositoryImpl(EntityManager em) {
		super(em, "ORDER BY");
	}

	@Override
	protected void filters(DocumentoTipoFilterDTO filter, ExecucaoQuery<DocumentoTipo> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}