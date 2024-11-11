package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.core.utils.MediaType;
import com.stfn2.ggas.domain.Rubrica;
import com.stfn2.ggas.domain.dtos.RubricaDTO;
import com.stfn2.ggas.domain.dtos.filter.RubricaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.RubricaBasicDTO;
import com.stfn2.ggas.repositories.RubricaRepository;
import com.stfn2.ggas.services.RubricaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/rubrica")
@Tag(name="Rubrica", description="EndPoints para gerenciamento de Rubrica")
public class RubricaController extends BaseController<Rubrica, RubricaDTO, RubricaBasicDTO, RubricaFilterDTO,
		RubricaRepository, RubricaService> {

	@Autowired
	RubricaService rubricaService;

	@GetMapping(value = "/comboTipo", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(
			summary = "Devolve rubrica com descrição e id dependendo do tipo, se é crédito ou débito",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public List<ComboDTO> getRubricasPorTipo(@RequestParam String tipo) {
		try{
			List<ComboDTO> rubricas = rubricaService.findRubricasPorTipo(tipo);
			return rubricas;
		}catch(IllegalArgumentException e){
			throw new RuntimeException("Tipo inválido. Deve ser 'credito' ou 'debito'.", e);
		}catch (Exception e) {
			throw new RuntimeException("Erro interno no servidor.", e);
		}
	}


}

