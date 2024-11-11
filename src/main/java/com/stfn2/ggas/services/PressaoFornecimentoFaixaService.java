package com.stfn2.ggas.services;

import org.springframework.stereotype.Service;

import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.core.abstractClasses.combo.ComboCustomDTO;
import com.stfn2.ggas.domain.PressaoFornecimentoFaixa;
import com.stfn2.ggas.domain.dtos.PressaoFornecimentoFaixaDTO;
import com.stfn2.ggas.domain.dtos.filter.PressaoFornecimentoFaixaFilterDTO;
import com.stfn2.ggas.domain.dtos.basic.PressaoFornecimentoFaixaBasicDTO;
import com.stfn2.ggas.services.componentes.combopersonalizado.pressaofornecimentofaixa.PressaoFornecimentoFaixaComponent;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.stfn2.ggas.repositories.PressaoFornecimentoFaixaRepository;

@Service
public class PressaoFornecimentoFaixaService extends BaseService<PressaoFornecimentoFaixa, PressaoFornecimentoFaixaDTO, PressaoFornecimentoFaixaBasicDTO, PressaoFornecimentoFaixaFilterDTO, PressaoFornecimentoFaixaRepository> {
    
    @Autowired
    private PressaoFornecimentoFaixaComponent pressaoFornecimentoFaixaComponent;
    
    public List<ComboCustomDTO> findFaixaPorPontoConsumo(Long pontoConsumoId){
        return pressaoFornecimentoFaixaComponent.findListaFaixaPorPontoConsumo(pontoConsumoId);
    }
    
    public PressaoFornecimentoFaixa findPressaoFornecimentoFaixaPorPontoConsumo(Long pontoConsumoId){
        return pressaoFornecimentoFaixaComponent.findPressaoFornecimentoFaixaPorPontoConsumo(pontoConsumoId);
    }
}