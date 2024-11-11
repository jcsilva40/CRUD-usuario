package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.DocumentoFiscal;
import com.stfn2.ggas.domain.dtos.DocumentoFiscalDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoFiscalBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoFiscalFilterDTO;
import com.stfn2.ggas.repositories.DocumentoFiscalRepository;
import com.stfn2.ggas.services.DocumentoFiscalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/documentoFiscal")
@Tag(name="DocumentoFiscal", description="EndPoints para gerenciamento de DocumentoFiscal")
public class DocumentoFiscalController extends BaseController<DocumentoFiscal, DocumentoFiscalDTO, DocumentoFiscalBasicDTO, DocumentoFiscalFilterDTO,
		DocumentoFiscalRepository,DocumentoFiscalService> {
}