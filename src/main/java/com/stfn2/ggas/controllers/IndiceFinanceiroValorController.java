package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.core.utils.MediaType;
import com.stfn2.ggas.domain.IndiceFinanceiro;
import com.stfn2.ggas.domain.IndiceFinanceiroValorNominal;
import com.stfn2.ggas.domain.dtos.filter.IndiceFinanceiroValorNominalEntreDatasFilterDTO;
import com.stfn2.ggas.domain.dtos.filter.IndiceFinanceiroValorNominalFilterDTO;
import com.stfn2.ggas.domain.dtos.IndiceFinanceiroValorNominalDTO;
import com.stfn2.ggas.domain.dtos.basic.IndiceFinanceiroValorNominalBasicDTO;
import com.stfn2.ggas.repositories.IndiceFinanceiroValorNominalRepository;
import com.stfn2.ggas.services.IndiceFinanceiroService;
import com.stfn2.ggas.services.IndiceFinanceiroValorService;
import com.stfn2.ggas.tools.ToolDate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/indiceFinanceiroValorNominal")
public class IndiceFinanceiroValorController extends BaseController<IndiceFinanceiroValorNominal, IndiceFinanceiroValorNominalDTO, IndiceFinanceiroValorNominalBasicDTO,
        IndiceFinanceiroValorNominalFilterDTO, IndiceFinanceiroValorNominalRepository,  IndiceFinanceiroValorService> {

    @Autowired
    IndiceFinanceiroService indiceFinanceiro;

    @PostMapping("/saveList")
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
    public ResponseEntity<Response<List<IndiceFinanceiroValorNominalDTO>>> updateList(HttpServletResponse resp, HttpServletRequest req,
                                                                                      @RequestBody List<IndiceFinanceiroValorNominalDTO> objDtos, BindingResult result) {
        Response<List<IndiceFinanceiroValorNominalDTO>> response = new Response<>();
        response.setData(service.createOrUpdateAll(objDtos));
        return ResponseEntity.ok(response);
    }


    @GetMapping("/getListEntreData")
    @Operation(
            summary = "Busca os objetos entre datas",
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Response<List<IndiceFinanceiroValorNominalDTO>>> getListEntreData(IndiceFinanceiroValorNominalEntreDatasFilterDTO filter) {
        Response<List<IndiceFinanceiroValorNominalDTO>> response = new Response<>();

        LocalDate inicio = null;
        LocalDate fim = null;

        IndiceFinanceiro indice = indiceFinanceiro.getById(filter.getIndiceFinanceiroId());

        if(indice.getMensal()){
            inicio = ToolDate.getDateFromStringYYYYHifenMM(filter.getDataInicio());
            fim = ToolDate.getDateFromStringYYYYHifenMM(filter.getDataFim());
            fim = ToolDate.lastDayOfMonth(fim);
        }else{
            inicio = ToolDate.getDateFromStringYYYYHifenMMHifenDD(filter.getDataInicio());
            fim = ToolDate.getDateFromStringYYYYHifenMMHifenDD(filter.getDataFim());
        }

        response.setData(service.getListEntreData(indice, inicio, fim));
        return ResponseEntity.ok(response);
    }


}
