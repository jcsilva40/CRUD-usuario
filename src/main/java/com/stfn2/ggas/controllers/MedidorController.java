package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.event.ObjectCreateEvent;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.domain.Medidor;
import com.stfn2.ggas.domain.dtos.MedidorDTO;
import com.stfn2.ggas.domain.dtos.basic.MedicaoHistoricoBasicDTO;
import com.stfn2.ggas.domain.dtos.basic.MedidorBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.MedicaoHistoricoFilterDTO;
import com.stfn2.ggas.domain.dtos.filter.MedidorFilterDTO;
import com.stfn2.ggas.repositories.MedidorRepository;
import com.stfn2.ggas.services.MedidorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/medidor")
@Tag(name="Medidor", description="EndPoints para gerenciamento de Medidor")
public class MedidorController extends BaseController<Medidor, MedidorDTO, MedidorBasicDTO, MedidorFilterDTO,
		MedidorRepository, MedidorService> {

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	protected MedidorService service;

	@Override
	public ResponseEntity<Response<MedidorDTO>> create(HttpServletResponse resp, HttpServletRequest req,
													   @Valid @RequestBody MedidorDTO objDto, BindingResult result) {

		if (objDto.getDescricao().isEmpty()) {
			String prefixo = objDto.getPrefixo();
			String sufixo = objDto.getSufixo();
			String numeroInicial = objDto.getNumeroInicial();
			int qtd = objDto.getQuantidadeLote();

			for (int i = 0; i < qtd; i++) {
				String numero = prefixo + numeroInicial + sufixo;
				objDto.setDescricao(numero);
				numeroInicial = String.valueOf(Integer.parseInt(numeroInicial) + 1);
				MedidorDTO objCreate = service.createOrUpdate(objDto);
				publisher.publishEvent(new ObjectCreateEvent(this, resp, objCreate.getId().toString()));
			}
		} else {
			MedidorDTO objCreate = service.createOrUpdate(objDto);
			publisher.publishEvent(new ObjectCreateEvent(this, resp, objCreate.getId().toString()));
		}

		Response<MedidorDTO> response = new Response<>();
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		@GetMapping(value = "/obterUltimaLeitura")
		public ResponseEntity<Response<MedicaoHistoricoBasicDTO>> obterUltimaLeitura(MedicaoHistoricoFilterDTO filter) {
			Response<MedicaoHistoricoBasicDTO> response = new Response<>();
			MedicaoHistoricoBasicDTO leitura = this.service.obterUltimaLeitura(filter);
			response.setData(leitura);
			return ResponseEntity.ok(response);
		}

	@GetMapping(value = "/obtermedidoresdisponiveis")
		public ResponseEntity<Response<List<MedidorDTO>>> obterListaMedidoresDisponiveis(){
			Response<List<MedidorDTO>> response = new Response<>();
			List<MedidorDTO> medidores = this.service.obterListaMedidoresDisponiveis();
			response.setData(medidores);
			return ResponseEntity.ok(response);
		}
	}