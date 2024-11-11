package com.stfn2.ggas.core.abstractClasses.entidade;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entidade")
public class EntidadeController {
    
    @Autowired
    private EntidadeService entidadeService;

    @GetMapping("/descricao")
    public ResponseEntity<?> getDescricaoEntidade(@RequestParam String entityName, @RequestParam Long id) {
        if(Objects.isNull(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String descricao = entidadeService.findDescricaoEnitdade(entityName, id);
        Map<String, Object> response = new HashMap<>();
        response.put("descricao", descricao);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/abreviado")    
    public ResponseEntity<?> getAbreviadoEntidade(@RequestParam String entityName, @RequestParam Long id) {
        if(Objects.isNull(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String descricao = entidadeService.findAbreviadoEnitdade(entityName, id);
        Map<String, Object> response = new HashMap<>();
        response.put("descricao", descricao);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
