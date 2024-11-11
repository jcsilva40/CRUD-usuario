package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.abstractClasses.combo.ComboCustomDTO;
import com.stfn2.ggas.domain.PressaoFornecimentoFaixa;
import com.stfn2.ggas.domain.dtos.filter.PressaoFornecimentoFaixaFilterDTO;
import com.stfn2.ggas.domain.dtos.PressaoFornecimentoFaixaDTO;
import com.stfn2.ggas.domain.dtos.basic.PressaoFornecimentoFaixaBasicDTO;
import com.stfn2.ggas.services.PressaoFornecimentoFaixaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stfn2.ggas.repositories.PressaoFornecimentoFaixaRepository;


@RestController
@RequestMapping(value = "/pressaoFornecimentoFaixa")
@Tag(name="PressaoFornecimentoFaixa", description="EndPoints para gerenciamento de PressaoFornecimentoFaixa")
public class PressaoFornecimentoFaixaController extends BaseController<PressaoFornecimentoFaixa, PressaoFornecimentoFaixaDTO, PressaoFornecimentoFaixaBasicDTO, PressaoFornecimentoFaixaFilterDTO,
		PressaoFornecimentoFaixaRepository, PressaoFornecimentoFaixaService> {
    
    @GetMapping(value= "/comboPersonalizado/faixaPorPontoConsumo")
    public List<ComboCustomDTO> getFaixaPorPontoConsumo(@RequestParam  Long idPontoConsumo) {
        return this.service.findFaixaPorPontoConsumo(idPontoConsumo);
    }
}