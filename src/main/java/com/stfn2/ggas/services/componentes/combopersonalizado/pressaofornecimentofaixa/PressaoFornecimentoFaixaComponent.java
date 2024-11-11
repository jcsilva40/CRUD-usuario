package com.stfn2.ggas.services.componentes.combopersonalizado.pressaofornecimentofaixa;

import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.combo.ComboCustomDTO;
import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.domain.PressaoFornecimentoFaixa;
import com.stfn2.ggas.domain.Unidade;
import com.stfn2.ggas.repositories.PontoConsumoRepository;
import com.stfn2.ggas.tools.ToolStr;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stfn2.ggas.repositories.PressaoFornecimentoFaixaRepository;
import java.util.Comparator;

@Component
public class PressaoFornecimentoFaixaComponent {
    
    @Autowired
    private PontoConsumoRepository pontoConsumoRepository;

    @Autowired
    private PressaoFornecimentoFaixaRepository pressaoFornecimentoFaixaRepository;
   
    public List<ComboCustomDTO> findListaFaixaPorPontoConsumo(Long pontoConsumoId){
       
        Long idSegmento = pontoConsumoRepository.findIdSegmentoPorPontoConsumo(pontoConsumoId);
        List<PressaoFornecimentoFaixa> listaFaixa = pressaoFornecimentoFaixaRepository.findListaPressaoFornecimentoFaixaPorSegmento(idSegmento);
        List<ComboCustomDTO> listaCombo = new ArrayList();
        if(listaFaixa.isEmpty()){
            return listaCombo;
        }
        for(PressaoFornecimentoFaixa faixa : listaFaixa){
            ComboCustomDTO combo = new ComboCustomDTO();
            combo.setValueCustom(faixa.getMedidaMinimo());
            StringBuilder descricaoFormatada = new StringBuilder();
            descricaoFormatada.append(ToolStr.converterCampoValorDecimalParaString("", faixa.getMedidaMinimo(), Constantes.LOCALE_PADRAO,
                                        Constantes.QUANTIDADE_CASAS_VALOR_DECIMAL));
            Unidade unidadePressao;
            if(!Objects.isNull(faixa.getUnidadePressao())){
                unidadePressao = faixa.getUnidadePressao();
                descricaoFormatada.append(" ").append(unidadePressao.getAbreviado());
                
            }
            combo.setDescricao(descricaoFormatada.toString());
            listaCombo.add(combo);
        }
        return listaCombo;
    }
    
    public PressaoFornecimentoFaixa findPressaoFornecimentoFaixaPorPontoConsumo(Long pontoConsumoId){
        Long idSegmento = pontoConsumoRepository.findIdSegmentoPorPontoConsumo(pontoConsumoId);
        List<PressaoFornecimentoFaixa> listaFaixa = pressaoFornecimentoFaixaRepository.findListaPressaoFornecimentoFaixaPorSegmento(idSegmento);
        if (!listaFaixa.isEmpty()) {
            PressaoFornecimentoFaixa maxFaixa = listaFaixa.stream()
                .max(Comparator.comparing(PressaoFornecimentoFaixa::getMedidaMinimo))
                .orElse(null);
            return maxFaixa;
        } else {
            return null;
        }
    }
}

