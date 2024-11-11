package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.domain.Contrato;
import com.stfn2.ggas.domain.dtos.ContratoDTO;
import com.stfn2.ggas.domain.dtos.filter.ContratoFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.ContratoBasicDTO;
import com.stfn2.ggas.repositories.ContratoRepository;
import com.stfn2.ggas.services.ContratoService;
import com.stfn2.ggas.core.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contrato")
@Tag(name="Contrato", description="EndPoints para gerenciamento de Contrato")
public class ContratoController extends BaseController<Contrato, ContratoDTO, ContratoBasicDTO, ContratoFilterDTO,
		ContratoRepository, ContratoService> {
    
    @GetMapping(value = "/comboPersonalizado/contratosFiltradosCade", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
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
    public List<ComboDTO> getContratosFiltradosCade(@RequestParam(required = false) Long contratoId, @RequestParam Long segmentoPaiId) {
        
        List<ComboDTO> obj = service.findContratosFiltradosCade(contratoId, segmentoPaiId);        
        return obj;
    }
    
}