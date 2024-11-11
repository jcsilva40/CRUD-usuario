package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.PontoConsumoTributoAliquota;
import com.stfn2.ggas.domain.dtos.PontoConsumoTributoAliquotaDTO;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoTributoAliquotaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.PontoConsumoTributoAliquotaBasicDTO;
import com.stfn2.ggas.repositories.PontoConsumoTributoAliquotaRepository;
import com.stfn2.ggas.services.PontoConsumoTributoAliquotaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/pontoConsumoTributoAliquota")
@Tag(name="PontoConsumoTributoAliquota", description="EndPoints para gerenciamento de PontoConsumoTributoAliquota")
public class PontoConsumoTributoAliquotaController extends BaseController<PontoConsumoTributoAliquota, PontoConsumoTributoAliquotaDTO, PontoConsumoTributoAliquotaBasicDTO, PontoConsumoTributoAliquotaFilterDTO,
		PontoConsumoTributoAliquotaRepository, PontoConsumoTributoAliquotaService> {
}

