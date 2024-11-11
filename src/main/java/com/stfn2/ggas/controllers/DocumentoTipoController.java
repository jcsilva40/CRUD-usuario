package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.DocumentoTipo;
import com.stfn2.ggas.domain.dtos.DocumentoTipoDTO;
import com.stfn2.ggas.domain.dtos.filter.DocumentoTipoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.DocumentoTipoBasicDTO;
import com.stfn2.ggas.repositories.DocumentoTipoRepository;
import com.stfn2.ggas.services.DocumentoTipoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/documentoTipo")
@Tag(name="DocumentoTipo", description="EndPoints para gerenciamento de DocumentoTipo")
public class DocumentoTipoController extends BaseController<DocumentoTipo, DocumentoTipoDTO, DocumentoTipoBasicDTO, DocumentoTipoFilterDTO,
		DocumentoTipoRepository, DocumentoTipoService> {
}