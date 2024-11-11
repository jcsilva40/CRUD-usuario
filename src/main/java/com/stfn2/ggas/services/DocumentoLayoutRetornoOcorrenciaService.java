package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.DocumentoLayoutRetornoOcorrencia;
import com.stfn2.ggas.domain.dtos.DocumentoLayoutRetornoOcorrenciaDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutRetornoOcorrenciaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoLayoutRetornoOcorrenciaBasicDTO;
import com.stfn2.ggas.repositories.DocumentoLayoutRetornoOcorrenciaRepository;

@Service
public class DocumentoLayoutRetornoOcorrenciaService extends BaseService<DocumentoLayoutRetornoOcorrencia, DocumentoLayoutRetornoOcorrenciaDTO, DocumentoLayoutRetornoOcorrenciaBasicDTO, DocumentoLayoutRetornoOcorrenciaFilterDTO, DocumentoLayoutRetornoOcorrenciaRepository> {

}