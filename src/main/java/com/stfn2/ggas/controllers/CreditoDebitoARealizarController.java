package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.domain.CreditoDebitoARealizar;
import com.stfn2.ggas.domain.dtos.CreditoDebitoARealizarDTO;
import com.stfn2.ggas.domain.dtos.RubricaDTO;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoARealizarFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.CreditoDebitoARealizarBasicDTO;
import com.stfn2.ggas.repositories.CreditoDebitoARealizarRepository;
import com.stfn2.ggas.services.CreditoDebitoARealizarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(value = "/creditoDebitoARealizar")
@Tag(name="CreditoDebitoARealizar", description="EndPoints para gerenciamento de CreditoDebitoARealizar")
public class CreditoDebitoARealizarController extends BaseController<CreditoDebitoARealizar, CreditoDebitoARealizarDTO, CreditoDebitoARealizarBasicDTO, CreditoDebitoARealizarFilterDTO,
		CreditoDebitoARealizarRepository, CreditoDebitoARealizarService> {

	@PostMapping("/alterarCobranca")
	@Operation(


			summary = "Salva e atualiza uma lista de objeto",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<Void> alterarCobrancaCredDebARealizar( @RequestBody CreditoDebitoARealizarDTO objDto) {
		try {
			this.service.alterarCobranca(objDto);
			return ResponseEntity.ok().build();
		}  catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/gerarSalvarParcelas")
	@Operation(
			summary = "Gera e salva parcelas de Crédito/Débito",
			responses = {
					@ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
					@ApiResponse(description = "Erro", responseCode = "400", content = @Content),
					@ApiResponse(description = "Erro Interno", responseCode = "500", content = @Content)
			}
	)
	public ResponseEntity<CreditoDebitoARealizarDTO> gerarSalvarParcelas(@RequestBody CreditoDebitoARealizarDTO dto) {
		try {
			CreditoDebitoARealizarDTO resultado = service.gerarSalvarParcelas(dto);
			return ResponseEntity.ok(resultado);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();		}
	}


	@GetMapping("/obterEnderecoECnpjCpf/{pontoConsumoId}")
	@Operation(
			summary = "obtém o endereço e cpf/cnpj a partir do ponto de consumo",
			responses = {
					@ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
					@ApiResponse(description = "Erro", responseCode = "400", content = @Content),
					@ApiResponse(description = "Erro Interno", responseCode = "500", content = @Content)
			}
	)
	public ResponseEntity<Map<String, Object>> obterEnderecoECnpjCpf(@PathVariable Long pontoConsumoId) {
		try {
			Map<String, Object> result = service.obterEnderecoECnpjCpfPorPontoConsumoId(pontoConsumoId);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/obterDadosRubrica/{rubricaId}")
	@Operation(
			summary = "Obtém os dados da Rubrica a partir do ID",
			responses = {
					@ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
					@ApiResponse(description = "Rubrica não encontrada", responseCode = "404", content = @Content),
					@ApiResponse(description = "Erro Interno", responseCode = "500", content = @Content)
			}
	)
	public ResponseEntity<RubricaDTO> obterDadosDaRubrica(@PathVariable Long rubricaId) {
		try {
			RubricaDTO rubricaDTO = service.obterDadosDaRubrica(rubricaId);
			return ResponseEntity.ok(rubricaDTO);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}


}