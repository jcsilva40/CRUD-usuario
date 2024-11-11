package com.stfn2.ggas.controllers;

import com.stfn2.ggas.core.abstractClasses.BaseController;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.models.Response;
import com.stfn2.ggas.domain.ArrecadadorContratoConvenio;
import com.stfn2.ggas.domain.dtos.filter.ArrecadadorContratoConvenioFilterDTO;
import com.stfn2.ggas.domain.dtos.ArrecadadorContratoConvenioDTO;
import com.stfn2.ggas.domain.dtos.basic.ArrecadadorContratoConvenioBasicDTO;
import com.stfn2.ggas.repositories.ArrecadadorContratoConvenioRepository;
import com.stfn2.ggas.services.ArrecadadorContratoConvenioService;
import com.stfn2.ggas.services.componentes.arrecadadorComponent.ArrecadadorComponent;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/arrecadadorContratoConvenio")
@Tag(name="ArrecadadorContratoConvenio", description="EndPoints para gerenciamento de ArrecadadorContratoConvenio")
public class ArrecadadorContratoConvenioController extends BaseController<ArrecadadorContratoConvenio, ArrecadadorContratoConvenioDTO, ArrecadadorContratoConvenioBasicDTO, ArrecadadorContratoConvenioFilterDTO,
		ArrecadadorContratoConvenioRepository, ArrecadadorContratoConvenioService> {
    
    @Autowired
    private ArrecadadorComponent arrecadadorComponent;
    
    @GetMapping(value = "/comboPersonalizado/arrecadadorPorCliente")
    public List<ComboDTO> getArrecadadorPorCliente(@RequestParam  Long tipoConvenioId, @RequestParam Long clienteId,@RequestParam Long pontoConsumoId) {
        return this.service.findArrecadadorPorCliente(tipoConvenioId, clienteId, pontoConsumoId);
    }
    
    @PostMapping(value = "/gerarArrecadadorBoleto")
    public ResponseEntity<?> gerarArrecadadorBoleto(@RequestParam Long clienteId, @RequestParam Long pontoConsumoId){
        Map<String, Object> response = new HashMap<>();        
	Long id = arrecadadorComponent.gerarArrecadador(clienteId, pontoConsumoId).getId();
        response.put("id", id);	
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping(value = "existeArrecadadorBoletoCliente")
    public ResponseEntity<?> existeArrecadadorBoletoCliente(@RequestParam Long clienteId, @RequestParam Long pontoConsumoId){
        Boolean existe = arrecadadorComponent.obterArrecadadorContratoConvenioPorClienteBancoCil(clienteId, pontoConsumoId);
        Map<String, Object> response = new HashMap<>();
        response.put("existe", existe);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}