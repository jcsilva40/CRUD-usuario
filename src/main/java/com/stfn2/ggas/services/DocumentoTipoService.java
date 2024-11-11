package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.DocumentoTipo;
import com.stfn2.ggas.domain.dtos.DocumentoTipoDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoTipoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoTipoBasicDTO;
import com.stfn2.ggas.repositories.DocumentoTipoRepository;

@Service
public class DocumentoTipoService extends BaseService<DocumentoTipo, DocumentoTipoDTO, DocumentoTipoBasicDTO, DocumentoTipoFilterDTO, DocumentoTipoRepository> {

}