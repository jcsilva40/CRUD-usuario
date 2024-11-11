package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.DocumentoLayoutErroOcorrencia;
import com.stfn2.ggas.domain.dtos.DocumentoLayoutErroOcorrenciaDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutErroOcorrenciaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoLayoutErroOcorrenciaBasicDTO;
import com.stfn2.ggas.repositories.DocumentoLayoutErroOcorrenciaRepository;

@Service
public class DocumentoLayoutErroOcorrenciaService extends BaseService<DocumentoLayoutErroOcorrencia, DocumentoLayoutErroOcorrenciaDTO, DocumentoLayoutErroOcorrenciaBasicDTO, DocumentoLayoutErroOcorrenciaFilterDTO, DocumentoLayoutErroOcorrenciaRepository> {

}