package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.DocumentoCobrancaItem;
import com.stfn2.ggas.domain.dtos.DocumentoCobrancaItemDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoCobrancaItemBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoCobrancaItemFilterDTO;
import com.stfn2.ggas.repositories.DocumentoCobrancaItemRepository;
import com.stfn2.ggas.services.DocumentoCobrancaItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/documentoCobrancaItem")
@Tag(name="DocumentoCobrancaItem", description="EndPoints para gerenciamento de DocumentoCobrancaItem")
public class DocumentoCobrancaItemController extends BaseController<DocumentoCobrancaItem, DocumentoCobrancaItemDTO, DocumentoCobrancaItemBasicDTO, DocumentoCobrancaItemFilterDTO,
		DocumentoCobrancaItemRepository,DocumentoCobrancaItemService> {
}