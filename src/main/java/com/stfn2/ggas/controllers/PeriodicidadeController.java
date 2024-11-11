package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.core.utils.MediaType;
import com.stfn2.ggas.domain.Periodicidade;
import com.stfn2.ggas.domain.dtos.filter.PeriodicidadeFilterDTO;
import com.stfn2.ggas.domain.dtos.PeriodicidadeDTO;
import com.stfn2.ggas.domain.dtos.basic.PeriodicidadeBasicDTO;
import com.stfn2.ggas.repositories.PeriodicidadeRepository;
import com.stfn2.ggas.services.PeriodicidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/periodicidade")
@Tag(name="periodicidade", description = "EndPoints para gerenciamento de periodicidade")
public class PeriodicidadeController
		extends BaseController<Periodicidade, PeriodicidadeDTO, PeriodicidadeBasicDTO,
		PeriodicidadeFilterDTO, PeriodicidadeRepository, PeriodicidadeService> {
    
    @GetMapping(value = "/periodicidadePorPontoConsumo", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(
            summary = "Busca data assinatura por id do ponto de consumo",
            responses = {
                @ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),}
    )
    public ResponseEntity<Response<Long>> getPeriodicidadePorPontoConsumo(@RequestParam Long pontoConsumoId) {
        Response<Long> response = new Response<>();
        Long obj = service.periodicidadePorPontoConsumo(pontoConsumoId);        
        response.setData(obj);
        return ResponseEntity.ok(response);
    }

}
