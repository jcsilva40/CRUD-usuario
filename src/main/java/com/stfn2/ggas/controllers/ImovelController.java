package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.domain.Imovel;
import com.stfn2.ggas.domain.dtos.ImovelDTO;
import com.stfn2.ggas.domain.dtos.PontoConsumoDTO;
import com.stfn2.ggas.domain.dtos.basic.ImovelBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.ImovelFilterDTO;
import com.stfn2.ggas.repositories.ImovelRepository;
import com.stfn2.ggas.services.ImovelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/imovel")
@Tag(name="Imovel", description="EndPoints para gerenciamento de Imovel")
public class ImovelController extends BaseController<Imovel, ImovelDTO, ImovelBasicDTO, ImovelFilterDTO,
		ImovelRepository, ImovelService> {
    
    @GetMapping(value= "/findImovelPorPontoConsumoId")
    public Long findImovelPorPontoConsumoId(@RequestParam  Long pontoConsumoId) {          
	Long imovelId = this.service.findImovelIdByPontoConsumoId(pontoConsumoId);
        return imovelId;
    }

    @GetMapping(value = "/obterimoveisdisponiveis")
    public ResponseEntity<Response<List<ImovelDTO>>> obterListaImoveisDisponiveis(){
        Response<List<ImovelDTO>> response = new Response<>();
        List<ImovelDTO> imoveis = this.service.obterListaImoveisDisponiveis();
        response.setData(imoveis);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/salvarPontoConsumo")
    public ResponseEntity<Response<Long>> savePontoConsumo(@RequestParam Long imovelId, @RequestBody PontoConsumoDTO pontoConsumo){
        Response<Long> response = new Response<>();
        Long pontoConsumoId = this.service.savePontoConsumo(imovelId, pontoConsumo);
        response.setData(pontoConsumoId);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/existCil", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Response<Boolean>> existCil(@RequestBody String cil) {
        Response<Boolean> response = new Response<>();
        Boolean exist = this.service.existCil(cil);
        response.setData(exist);
        return ResponseEntity.ok(response);
    }
}