package com.stfn2.ggas.services.componentes.calculotarifa.impl;

import com.stfn2.ggas.domain.enumTypes.TipoCalculoEnum;
import com.stfn2.ggas.services.componentes.calculotarifa.vo.DadosFaixaVO;
import com.stfn2.ggas.services.componentes.calculotarifa.TipoCalculoInterface;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CalculoPorFaixa implements TipoCalculoInterface {

   @Override
   public List<DadosFaixaVO> calcular(BigDecimal volume) {
      return List.of();
   }

   @Override
   public Long tipoCalculo() {
      return TipoCalculoEnum.FAIXA.getId();
   }

   @Override
   public String descricao() {
      return TipoCalculoEnum.FAIXA.getDescricao();
   }
}
