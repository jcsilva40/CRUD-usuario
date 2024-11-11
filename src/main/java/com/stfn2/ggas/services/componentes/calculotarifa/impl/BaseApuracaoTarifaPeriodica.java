package com.stfn2.ggas.services.componentes.calculotarifa.impl;

import com.stfn2.ggas.domain.PontoConsumo;
import com.stfn2.ggas.domain.TarifaVigencia;
import com.stfn2.ggas.domain.TributoAliquota;
import com.stfn2.ggas.domain.enumTypes.BaseApuracaoEnum;
import com.stfn2.ggas.services.componentes.calculotarifa.BaseApuracaoTarifaInterface;
import com.stfn2.ggas.services.componentes.calculotarifa.TipoCalculoInterface;
import com.stfn2.ggas.services.componentes.calculotarifa.vo.DadosFaixaVO;
import com.stfn2.ggas.services.componentes.calculotarifa.vo.DadosVigenciaVO;
import com.stfn2.ggas.services.componentes.calculotarifa.vo.PeriodoVO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class BaseApuracaoTarifaPeriodica implements BaseApuracaoTarifaInterface {

   @Override
   public DadosVigenciaVO calcular(List<TipoCalculoInterface> tiposCalculo, PontoConsumo pontoConsumo,
                                   Long itemFatura, TarifaVigencia tarifaVigencia,
                                   List<TributoAliquota> colecaoTributoAliquota, BigDecimal consumoPeriodo,
                                   PeriodoVO periodo, LocalDate ultimaLeitura) {
      DadosVigenciaVO dadosVigencia = new DadosVigenciaVO();
      TipoCalculoInterface tipoCalculo = this.getTipoCalculo(tiposCalculo, tarifaVigencia.getTipoCalculo().getId());
      List<DadosFaixaVO> dadosFaixa = tipoCalculo.calcular(consumoPeriodo);

      return dadosVigencia;
   }

   @Override
   public Long baseApuracao() {
      return BaseApuracaoEnum.PERIODICO.getId();
   }

   @Override
   public String descricao() {
      return BaseApuracaoEnum.PERIODICO.getDescricao();
   }
}
