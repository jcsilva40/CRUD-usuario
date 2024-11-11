package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutEnvioOcorrenciaFilterDTO;
import com.stfn2.ggas.domain.DocumentoLayoutEnvioOcorrencia;
import jakarta.persistence.EntityManager;

public class DocumentoLayoutEnvioOcorrenciaRepositoryImpl extends IRepository<DocumentoLayoutEnvioOcorrencia, DocumentoLayoutEnvioOcorrenciaFilterDTO> {

	public DocumentoLayoutEnvioOcorrenciaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(DocumentoLayoutEnvioOcorrenciaFilterDTO filter, ExecucaoQuery<DocumentoLayoutEnvioOcorrencia> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}