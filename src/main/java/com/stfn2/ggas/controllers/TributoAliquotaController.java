package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.core.utils.MediaType;
import com.stfn2.ggas.domain.TributoAliquota;
import com.stfn2.ggas.domain.dtos.filter.TributoAliquotaFilterDTO;
import com.stfn2.ggas.domain.dtos.TributoAliquotaDTO;
import com.stfn2.ggas.domain.dtos.basic.TributoAliquotaBasicDTO;
import com.stfn2.ggas.repositories.TributoAliquotaRepository;
import com.stfn2.ggas.services.TributoAliquotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumSet;


@RestController
@RequestMapping(value = "/tributosAliquota")
@Tag(name="TributoAliquota", description="EndPoints para gerenciamento de TributoAliquota")
public class TributoAliquotaController extends BaseController<TributoAliquota, TributoAliquotaDTO, TributoAliquotaBasicDTO, TributoAliquotaFilterDTO,
		TributoAliquotaRepository, TributoAliquotaService> {

	@GetMapping(value="/enuns", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	@Operation(
			summary = "Devolve um objeto com descrição e id para montagem de um ComboBox",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<Response<EnumSet<?>>> getEnuns() {
		Response<EnumSet<?>> response = new Response<>();
		response.setData(service.obterEnuns());
		return ResponseEntity.ok(response);
	}
}

