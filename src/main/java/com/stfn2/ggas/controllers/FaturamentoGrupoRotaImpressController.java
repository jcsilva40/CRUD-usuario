package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.FaturamentoGrupoRotaImpress;
import com.stfn2.ggas.domain.dtos.FaturamentoGrupoRotaImpressDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoGrupoRotaImpressFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoGrupoRotaImpressBasicDTO;
import com.stfn2.ggas.repositories.FaturamentoGrupoRotaImpressRepository;
import com.stfn2.ggas.services.FaturamentoGrupoRotaImpressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/faturamentoGrupoRotaImpress")
@Tag(name="FaturamentoGrupoRotaImpress", description="EndPoints para gerenciamento de FaturamentoGrupoRotaImpress")
public class FaturamentoGrupoRotaImpressController extends BaseController<FaturamentoGrupoRotaImpress, FaturamentoGrupoRotaImpressDTO, FaturamentoGrupoRotaImpressBasicDTO, FaturamentoGrupoRotaImpressFilterDTO,
		FaturamentoGrupoRotaImpressRepository, FaturamentoGrupoRotaImpressService> {
}