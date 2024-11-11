package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.core.utils.MediaType;
import com.stfn2.ggas.domain.CampanhaDesconto;
import com.stfn2.ggas.domain.dtos.CampanhaDescontoDTO;
import com.stfn2.ggas.domain.dtos.SegmentoPaiDTO;
import com.stfn2.ggas.domain.dtos.basic.CampanhaDescontoBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.CampanhaDescontoFilterDTO;
import com.stfn2.ggas.repositories.CampanhaDescontoRepository;
import com.stfn2.ggas.services.CampanhaDescontoService;
import com.stfn2.ggas.services.componentes.campanhaDesconto.CampanhaDescontoComponent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/campanhaDesconto")
@Tag(name="CampanhaDesconto", description="EndPoints para gerenciamento de CampanhaDesconto")
public class CampanhaDescontoController extends BaseController<CampanhaDesconto, CampanhaDescontoDTO, CampanhaDescontoBasicDTO, CampanhaDescontoFilterDTO,
		CampanhaDescontoRepository,CampanhaDescontoService> {
    
    @Autowired
    private CampanhaDescontoComponent campanhaDescontoComponent;
    
    @GetMapping(value = "/comboPersonalizado/campanhaDescontoFormatada", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(
            summary = "Busca combo de campanha de desconto com descrição formatada",
            responses = {
                @ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),}
    )
    public List<ComboDTO> getCampanhaDescontoFormatada() {
        return this.service.findCampanhaDescontoFormatada();
    }
    
    @GetMapping(value = "/consultarSegmentoPaiPorCampanhaDesconto", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(
            summary = "Busca dados de segmento pai pela campanha de desconto",
            responses = {
                @ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),}
    )
    public ResponseEntity<Response<SegmentoPaiDTO>> findSegmentoPaiPorCampanhaDesconto(@RequestParam Long campanhaDescontoId) {
        Response<SegmentoPaiDTO> response = new Response<>();
        SegmentoPaiDTO obj = campanhaDescontoComponent.findSegmentoPaiPorCampanhaDesconto(campanhaDescontoId);        
        response.setData(obj);
        return ResponseEntity.ok(response);
    }
}