package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.domain.Cliente;
import com.stfn2.ggas.domain.dtos.filter.ClienteFilterDTO;
import com.stfn2.ggas.domain.dtos.ClienteDTO;
import com.stfn2.ggas.domain.dtos.basic.ClienteBasicDTO;
import com.stfn2.ggas.repositories.ClienteRepository;
import com.stfn2.ggas.services.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/clientes")
@Tag(name="Cliente", description="EndPoints para gerenciamento de Cliente")
public class ClienteController extends BaseController<Cliente, ClienteDTO, ClienteBasicDTO, ClienteFilterDTO, ClienteRepository,
		ClienteService> {
    
    @GetMapping(value = "/comboPersonalizado/listaCliente")
    public List<ComboDTO> getArrecadadorPorCliente() {
        return this.service.findAllCliente();
    }

}

