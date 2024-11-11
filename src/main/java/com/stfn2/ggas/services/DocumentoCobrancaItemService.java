package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.DocumentoCobrancaItem;
import com.stfn2.ggas.domain.dtos.DocumentoCobrancaItemDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoCobrancaItemBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoCobrancaItemFilterDTO;
import com.stfn2.ggas.repositories.DocumentoCobrancaItemRepository;

@Service
public class DocumentoCobrancaItemService extends BaseService<DocumentoCobrancaItem, DocumentoCobrancaItemDTO, DocumentoCobrancaItemBasicDTO, DocumentoCobrancaItemFilterDTO, DocumentoCobrancaItemRepository> {

}