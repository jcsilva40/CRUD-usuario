package com.stfn2.ggas.core.abstractClasses.combo;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/combo")
public class ComboController {

    @Autowired
    private ComboService genericService;

    @GetMapping("/{entityName}")
    public List<ComboDTO> getIdAndDescricao(@PathVariable String entityName) {
        return genericService.findIdAndDescricao(entityName);
    }
    
    @GetMapping("/entidadeAbreviado/{entityName}")
    public List<ComboDTO> getEntidadeAbreviado(@PathVariable String entityName) {
        return genericService.findEntidadeAbreviado(entityName);
    }
    
    @GetMapping("/entidadeDescricaoById/{entityName}")
    public List<ComboDTO> getIdAndDescricaoById(@PathVariable String entityName, @RequestParam Long id) {
        return genericService.findIdAndDescricaoById(entityName, id);
    }
    
    @GetMapping("/entidadeAbreviadoById/{entityName}")
    public List<ComboDTO> getEntidadeAbreviadoById(@PathVariable String entityName, @RequestParam Long id) {
        return genericService.findEntidadeAbreviadoById(entityName, id);
    }
    
    @GetMapping("/entidadeConteudo/{id}")
    public List<ComboDTO> getEntidadeConteudo(@PathVariable Long id) {
        return genericService.findEntidadeConteudo(id);
    }
    @GetMapping("/entidadeConteudoAbreviado/{id}")
    public List<ComboDTO> getEntidadeConteudoAbreviado(@PathVariable Long id) {
        return genericService.findEntidadeConteudoAbreviado(id);
    }
    
    @GetMapping("/unidade/{id}")
    public List<ComboDTO> getUnidade(@PathVariable Long id) {
        return genericService.findUnidadePorEntidadeClasse(id);
    }
    @GetMapping("/unidadeAbreviado/{id}")
    public List<ComboDTO> getUnidadeAbreviado(@PathVariable Long id) {
        return genericService.findUnidadePorEntidadeClasseAbreviado(id);
    }
    
    @GetMapping("/comboEnum/{enumName}")
    public List<ComboDTO> getIdAndDescricaoEnum(@PathVariable String enumName) {
        return genericService.findIdAndDescricaoEnum(enumName);
    }
    @GetMapping("/comboAbreviadoEnum/{enumName}")
    public List<ComboDTO> getIdAndAbreviadoEnum(@PathVariable String enumName) {
        return genericService.findIdAndAbreviadoEnum(enumName);
    }
    @GetMapping("/enum/{enumName}")
    public List<Map<String, Object>> getAllEnum(@PathVariable String enumName) {
        return genericService.findAllEnum(enumName);
    }
}
