package com.stfn2.ggas.core.abstractClasses.enumType;

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
@RequestMapping("/enum")
public class EnumController {
    
    @Autowired
    private EnumService enumService;

    @GetMapping("/descricao")
    public ResponseEntity<?> getDescricaoEnum(@RequestParam String enumName, @RequestParam Long id) {
        if(Objects.isNull(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String descricao = enumService.findDescricaoEnum(enumName, id);
        Map<String, Object> response = new HashMap<>();
        response.put("descricao", descricao);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/abreviado")    
    public ResponseEntity<?> getAbreviadoEnum(@RequestParam String enumName, @RequestParam Long id) {
        if(Objects.isNull(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String descricao = enumService.findAbreviadoEnum(enumName, id);
        Map<String, Object> response = new HashMap<>();
        response.put("descricao", descricao);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
