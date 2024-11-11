package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.DocumentoImpressaoLayout;
import com.stfn2.ggas.domain.dtos.DocumentoImpressaoLayoutDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoImpressaoLayoutBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoImpressaoLayoutFilterDTO;
import com.stfn2.ggas.repositories.DocumentoImpressaoLayoutRepository;
import com.stfn2.ggas.services.DocumentoImpressaoLayoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/documentoImpressaoLayout")
@Tag(name="DocumentoImpressaoLayout", description="EndPoints para gerenciamento de DocumentoImpressaoLayout")
public class DocumentoImpressaoLayoutController extends BaseController<DocumentoImpressaoLayout, DocumentoImpressaoLayoutDTO, DocumentoImpressaoLayoutBasicDTO, DocumentoImpressaoLayoutFilterDTO,
		DocumentoImpressaoLayoutRepository,DocumentoImpressaoLayoutService> {
}