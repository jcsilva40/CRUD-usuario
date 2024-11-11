package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.core.utils.MediaType;
import com.stfn2.ggas.domain.PontoConsumo;
import com.stfn2.ggas.domain.dtos.PontoConsumoDTO;
import com.stfn2.ggas.domain.dtos.filter.PontoConsumoFilterDTO;
import com.stfn2.ggas.domain.dtos.EnderecoDTO;
import com.stfn2.ggas.domain.dtos.basic.PontoConsumoBasicDTO;
import com.stfn2.ggas.repositories.PontoConsumoRepository;
import com.stfn2.ggas.services.PontoConsumoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pontoConsumo")
@Tag(name = "PontoConsumo", description = "EndPoints para gerenciamento de PontoConsumo")
public class PontoConsumoController extends BaseController<PontoConsumo, PontoConsumoDTO, PontoConsumoBasicDTO, PontoConsumoFilterDTO, PontoConsumoRepository, PontoConsumoService> {

    @GetMapping(value = "/endereco/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(
            summary = "Busca objeto por ID",
            responses = {
                @ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),}
    )
    public ResponseEntity<Response<EnderecoDTO>> findEnderecoByPontoConsumo(@PathVariable Long id) {
        Response<EnderecoDTO> response = new Response<>();
        EnderecoDTO obj = service.findEnderecoByPontoConsumo(id);        
        response.setData(obj);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/comboPersonalizado/listaPontoConsumoSemContratoAtivo")
    public List<ComboDTO> getPontoConsumoSemContratoAtivo() {
        return this.service.findPontoConsumoSemContratoAtivo();
    }
    
    @GetMapping(value = "pontoConsumoComContratoSemLiberacaoGas")
    public ResponseEntity<?> pontoConsumoComContratoSemLiberacaoGas(@RequestParam Long pontoConsumoId){
        Boolean existe = service.pontoConsumoComContratoSemLiberacaoGas(pontoConsumoId);
        Map<String, Object> response = new HashMap<>();
        response.put("existe", existe);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping(value = "/consultarExistenciaDataAssinatura", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
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
    public ResponseEntity<Response<LocalDate>> consultarExistenciaDeDataAssinaturaNoImovel(@RequestParam Long pontoConsumoId) {
        Response<LocalDate> response = new Response<>();
        LocalDate obj = service.consultarExistenciaDeDataAssinaturaNoImovel(pontoConsumoId);        
        response.setData(obj);
        return ResponseEntity.ok(response);
    }
}
