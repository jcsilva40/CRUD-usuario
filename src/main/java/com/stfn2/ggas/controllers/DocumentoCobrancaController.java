package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.domain.dtos.DocumentoCobrancaDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoCobrancaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoCobrancaFilterDTO;
import com.stfn2.ggas.repositories.DocumentoCobrancaRepository;
import com.stfn2.ggas.services.DocumentoCobrancaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/documentoCobranca")
@Tag(name="DocumentoCobranca", description="EndPoints para gerenciamento de DocumentoCobranca")
public class DocumentoCobrancaController extends BaseController<DocumentoCobranca, DocumentoCobrancaDTO, DocumentoCobrancaBasicDTO, DocumentoCobrancaFilterDTO,
		DocumentoCobrancaRepository,DocumentoCobrancaService> {
}