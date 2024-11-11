package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.IndiceFinanceiro;
import com.stfn2.ggas.domain.dtos.filter.IndiceFinanceiroFilterDTO;
import com.stfn2.ggas.domain.dtos.IndiceFinanceiroDTO;
import com.stfn2.ggas.domain.dtos.basic.IndiceFinanceiroBasicDTO;
import com.stfn2.ggas.repositories.IndiceFinanceiroRepository;
import com.stfn2.ggas.services.IndiceFinanceiroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/indicesfinanceiros")
@Tag(name="IndiceFinanceiro", description = "EndPoints para gerenciamento de IndiceFinanceiro")
public class IndiceFinanceiroController
        extends BaseController<IndiceFinanceiro, IndiceFinanceiroDTO, IndiceFinanceiroBasicDTO,
        IndiceFinanceiroFilterDTO, IndiceFinanceiroRepository,
        IndiceFinanceiroService> {

}
