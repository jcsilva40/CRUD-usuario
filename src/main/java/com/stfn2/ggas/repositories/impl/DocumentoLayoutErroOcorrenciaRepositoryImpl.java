package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutErroOcorrenciaFilterDTO;
import com.stfn2.ggas.domain.DocumentoLayoutErroOcorrencia;
import jakarta.persistence.EntityManager;

public class DocumentoLayoutErroOcorrenciaRepositoryImpl extends IRepository<DocumentoLayoutErroOcorrencia, DocumentoLayoutErroOcorrenciaFilterDTO> {

	public DocumentoLayoutErroOcorrenciaRepositoryImpl(EntityManager em) {
		super(em, "id");
	}

	@Override
	protected void filters(DocumentoLayoutErroOcorrenciaFilterDTO filter, ExecucaoQuery<DocumentoLayoutErroOcorrencia> execute) {
		 //addFilterPositive(execute, "id", filter.getId());
	}
}