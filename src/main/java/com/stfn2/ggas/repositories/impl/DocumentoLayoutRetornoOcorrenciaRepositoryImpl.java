package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutRetornoOcorrenciaFilterDTO;
import com.stfn2.ggas.domain.DocumentoLayoutRetornoOcorrencia;
import jakarta.persistence.EntityManager;

public class DocumentoLayoutRetornoOcorrenciaRepositoryImpl extends IRepository<DocumentoLayoutRetornoOcorrencia, DocumentoLayoutRetornoOcorrenciaFilterDTO> {

	public DocumentoLayoutRetornoOcorrenciaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(DocumentoLayoutRetornoOcorrenciaFilterDTO filter, ExecucaoQuery<DocumentoLayoutRetornoOcorrencia> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}