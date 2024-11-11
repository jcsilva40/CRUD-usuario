package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.DocumentoLayoutRegistro;
import com.stfn2.ggas.domain.dtos.DocumentoLayoutRegistroDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutRegistroFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoLayoutRegistroBasicDTO;
import com.stfn2.ggas.repositories.DocumentoLayoutRegistroRepository;

@Service
public class DocumentoLayoutRegistroService extends BaseService<DocumentoLayoutRegistro, DocumentoLayoutRegistroDTO, DocumentoLayoutRegistroBasicDTO, DocumentoLayoutRegistroFilterDTO, DocumentoLayoutRegistroRepository> {

}