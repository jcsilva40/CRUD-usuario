package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.domain.Tarifa;
import com.stfn2.ggas.domain.dtos.TarifaDTO;
import com.stfn2.ggas.domain.dtos.filter.TarifaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.TarifaBasicDTO;
import com.stfn2.ggas.repositories.TarifaRepository;
import com.stfn2.ggas.services.TarifaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/tarifas")
@Tag(name="Tarifa", description="EndPoints para gerenciamento de Tarifa")
public class TarifaController extends BaseController<Tarifa, TarifaDTO, TarifaBasicDTO, TarifaFilterDTO,
		TarifaRepository, TarifaService> {
    
    @GetMapping(value= "/comboPersonalizado/tarifaPorPontoConsumo")
    public List<ComboDTO> getTarifaPorPontoConsumo(@RequestParam  Long pontoConsumoId) {
        return this.service.findTarifaPorPontoConsumo(pontoConsumoId);
    }
}

