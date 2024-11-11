package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.utils.MediaType;
import com.stfn2.ggas.domain.BaseCep;
import com.stfn2.ggas.domain.dtos.BaseCepDTO;
import com.stfn2.ggas.domain.dtos.filter.BaseCepFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.BaseCepBasicDTO;
import com.stfn2.ggas.repositories.BaseCepRepository;
import com.stfn2.ggas.services.BaseCepService;
import com.stfn2.ggas.webservices.cep.viacep.ViaCepWebService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/baseCep")
@Tag(name = "ViaCep", description = "EndPoints para gerenciamento de ViaCep")
public class ViaCepController extends BaseController<BaseCep, BaseCepDTO, BaseCepBasicDTO, BaseCepFilterDTO,
        BaseCepRepository, BaseCepService> {

    @Autowired
    ViaCepWebService webService;

    @GetMapping(value = "/api/{cep}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<BaseCepDTO> findAPI(@PathVariable String cep) {
            BaseCepDTO cepVia = service.findCep(cep);
        return ResponseEntity.ok(cepVia);
    }
}


