package com.stfn2.ggas.repositories.impl;

import com.stfn2.ggas.core.abstractClasses.IRepository;
import com.stfn2.ggas.core.capsulas.ExecucaoQuery;
import com.stfn2.ggas.domain.dtos.filter.CertificadoMedidorFilterDTO;
import com.stfn2.ggas.domain.CertificadoMedidor;
import jakarta.persistence.EntityManager;

public class CertificadoMedidorRepositoryImpl extends IRepository<CertificadoMedidor, CertificadoMedidorFilterDTO> {

	public CertificadoMedidorRepositoryImpl(EntityManager em) {
		super(em, "descricao");
	}

	@Override
	protected void filters(CertificadoMedidorFilterDTO filter, ExecucaoQuery<CertificadoMedidor> execute) {
		 addFilterLike(execute, "descricao", filter.getDescricao());
	}
}