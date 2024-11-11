package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.core.utils.MediaType;
import com.stfn2.ggas.domain.LiberacaoCronogramaFaturamento;
import com.stfn2.ggas.domain.dtos.DownloadDTO;
import com.stfn2.ggas.domain.dtos.LiberacaoCronogramaFaturamentoDTO;
import com.stfn2.ggas.domain.dtos.basic.LiberacaoCronogramaFaturamentoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.LiberacaoCronogramaFaturamentoFilterDTO;
import com.stfn2.ggas.repositories.LiberacaoCronogramaFaturamentoRepository;
import com.stfn2.ggas.services.LiberacaoCronogramaFaturamentoService;
import com.stfn2.ggas.services.componentes.vo.LiberacaoCronogramaVO;
import com.stfn2.ggas.services.componentes.vo.ValidacaoLiberacaoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/liberacaocronogramafaturamento")
@Tag(name="LiberacaoCronogramaFaturamento", description="EndPoints para gerenciamento de Liberação de Cronogramas de " +
		"Faturamento")
public class LiberacaoCronogramaFaturamentoController extends BaseController<LiberacaoCronogramaFaturamento, LiberacaoCronogramaFaturamentoDTO, LiberacaoCronogramaFaturamentoBasicDTO, LiberacaoCronogramaFaturamentoFilterDTO,
		LiberacaoCronogramaFaturamentoRepository, LiberacaoCronogramaFaturamentoService> {


	@GetMapping(value = "/carregarliberacoes", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca todas as liberações de acordo com o filtro",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<Response<List<LiberacaoCronogramaVO>>> carregarLiberacoes(LiberacaoCronogramaFaturamentoFilterDTO filter) {
		Response<List<LiberacaoCronogramaVO>> response = new Response<>();
		response.setData(service.carregarLiberacoesCronograma(filter));
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/bloquearFatura", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca todas as liberações de acordo com o filtro",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<String> bloquearFatura(@Valid @RequestBody Long id, BindingResult result) {
		service.bloquearFatura(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/liberarFatura", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca todas as liberações de acordo com o filtro",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<String> liberarFatura(@Valid @RequestBody Long id, BindingResult result) {
		service.liberarFatura(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/validarEtapa", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	@Operation(
			summary = "Busca todas as liberações de acordo com o filtro",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<String> validarEtapa(LiberacaoCronogramaFaturamentoFilterDTO filter) {

		service.validarEtapa(filter.getId(), filter.getIdUser());
		return ResponseEntity.ok().build();
	}

	@GetMapping("downloadPlanilhaValidacao")
	public ResponseEntity<Response<DownloadDTO>> exportar(LiberacaoCronogramaFaturamentoFilterDTO filter) {
		Response<DownloadDTO> response = new Response<>();
		DownloadDTO planilha = this.service.downloadPlanilhaValidacao(filter);
		response.setData(planilha);
		return ResponseEntity.ok(response);
	}

	@GetMapping("obterFaturaLiberacao")
	public ResponseEntity<Response<DownloadDTO>> obterFatura(LiberacaoCronogramaFaturamentoFilterDTO filter) {
		Response<DownloadDTO> response = new Response<>();
		DownloadDTO pdfFatura = this.service.obterFaturas(filter);
		response.setData(pdfFatura);
		return ResponseEntity.ok(response);
	}

	@GetMapping("obterFaturaPorGrupoFaturamento")
	public ResponseEntity<Response<DownloadDTO>>downloadFaturasPorGrupoFaturamento (ValidacaoLiberacaoVO filter) {
		Response<DownloadDTO> response = new Response<>();
		DownloadDTO pdfFaturas = this.service.downloadFaturasPorGrupoFaturamento(filter);
		response.setData(pdfFaturas);
		return ResponseEntity.ok(response);
	}

}

