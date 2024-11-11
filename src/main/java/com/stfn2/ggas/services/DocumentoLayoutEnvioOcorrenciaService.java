package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.DocumentoLayoutEnvioOcorrencia;
import com.stfn2.ggas.domain.dtos.DocumentoLayoutEnvioOcorrenciaDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutEnvioOcorrenciaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoLayoutEnvioOcorrenciaBasicDTO;
import com.stfn2.ggas.repositories.DocumentoLayoutEnvioOcorrenciaRepository;

@Service
public class DocumentoLayoutEnvioOcorrenciaService extends BaseService<DocumentoLayoutEnvioOcorrencia, DocumentoLayoutEnvioOcorrenciaDTO, DocumentoLayoutEnvioOcorrenciaBasicDTO, DocumentoLayoutEnvioOcorrenciaFilterDTO, DocumentoLayoutEnvioOcorrenciaRepository> {

}