package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.domain.ClienteContato;
import com.stfn2.ggas.domain.dtos.ClienteContatoDTO;
import com.stfn2.ggas.domain.dtos.basic.ClienteContatoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ClienteContatoFilterDTO;
import com.stfn2.ggas.repositories.ClienteContatoRepository;
import com.stfn2.ggas.services.ClienteContatoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/clientecontato")
@Tag(name="Cliente", description="EndPoints para gerenciamento de ClienteContato")
public class ClienteContatoController extends BaseController<ClienteContato, ClienteContatoDTO, ClienteContatoBasicDTO, ClienteContatoFilterDTO,
		ClienteContatoRepository, ClienteContatoService> {

	@GetMapping(value= "/cliente/{id}")
	public ResponseEntity<Response<List<ClienteContatoBasicDTO>>> getContatosCliente(@PathVariable Long id) {
		Response<List<ClienteContatoBasicDTO>> response = new Response<>();
		response.setData(service.buscarClienteContato(id));
		return ResponseEntity.ok(response);
	}

}

