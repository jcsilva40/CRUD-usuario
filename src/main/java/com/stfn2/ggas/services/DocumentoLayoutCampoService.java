package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.DocumentoLayoutCampo;
import com.stfn2.ggas.domain.dtos.DocumentoLayoutCampoDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutCampoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoLayoutCampoBasicDTO;
import com.stfn2.ggas.repositories.DocumentoLayoutCampoRepository;

@Service
public class DocumentoLayoutCampoService extends BaseService<DocumentoLayoutCampo, DocumentoLayoutCampoDTO, DocumentoLayoutCampoBasicDTO, DocumentoLayoutCampoFilterDTO, DocumentoLayoutCampoRepository> {

}