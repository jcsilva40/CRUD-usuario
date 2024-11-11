package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.domain.Feriado;
import com.stfn2.ggas.domain.dtos.FeriadoDTO;
import com.stfn2.ggas.domain.dtos.filter.FeriadoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.FeriadoBasicDTO;
import com.stfn2.ggas.repositories.FeriadoRepository;
import com.stfn2.ggas.services.FeriadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/feriado")
@Tag(name="Feriado", description="EndPoints para gerenciamento de Feriado")
public class FeriadoController extends BaseController<Feriado, FeriadoDTO, FeriadoBasicDTO, FeriadoFilterDTO,
		FeriadoRepository, FeriadoService> {

	@PostMapping("/gerarFeriadosEmAnos")
	@Operation(
			summary = "Cria lista de mesmos feriados dentro de um intevalo de anos",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<Response<List<FeriadoDTO>>> gerarFeriadosEmAnos(@RequestBody FeriadoDTO feriado) {
		Response<List<FeriadoDTO>> response = new Response<>();

		response.setData(service.gerarListaFeriadosRecorrentes(feriado));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/deleteRecorrente")
	@Operation(
			summary = "Deleta do banco de dados os feriados recorrentes",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity< ResponseEntity<Void>> deleteFeriadoRecorrente(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "anosFeriado", required = false) Long anosFeriado) {
		Response<List<FeriadoDTO>> response = new Response<>();
		service.deleteFeriadoRecorrente(id, anosFeriado);
		return ResponseEntity.noContent().build();

	}

}