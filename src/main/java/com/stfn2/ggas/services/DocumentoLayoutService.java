package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.DocumentoLayout;
import com.stfn2.ggas.domain.dtos.DocumentoLayoutDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoLayoutFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoLayoutBasicDTO;
import com.stfn2.ggas.repositories.DocumentoLayoutRepository;

@Service
public class DocumentoLayoutService extends BaseService<DocumentoLayout, DocumentoLayoutDTO, DocumentoLayoutBasicDTO, DocumentoLayoutFilterDTO, DocumentoLayoutRepository> {

   public DocumentoLayout obterDocumentoLayoutPorConvenio(Long arrecadadorContratoConvenioId){
      DocumentoLayout documentoLayout = new DocumentoLayout();

      documentoLayout = repository.obterDocumentoLayoutPorConvenio(arrecadadorContratoConvenioId);

      return documentoLayout;
   }
}