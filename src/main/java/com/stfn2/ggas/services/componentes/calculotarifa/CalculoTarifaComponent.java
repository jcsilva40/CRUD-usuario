package com.stfn2.ggas.services.componentes.calculotarifa;

import com.stfn2.ggas.domain.PontoConsumo;
import com.stfn2.ggas.domain.TarifaVigencia;
import com.stfn2.ggas.domain.TributoAliquota;
import com.stfn2.ggas.services.componentes.calculotarifa.vo.PeriodoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class CalculoTarifaComponent {

   List<BaseApuracaoTarifaInterface> baseApuracaoTarifaInterfaces;
   List<TipoCalculoInterface> tipoCalculoInterfaces;

   @Autowired
   CalculoTarifaComponent(List<BaseApuracaoTarifaInterface> baseApuracaoTarifaInterfaces,
                          List<TipoCalculoInterface> tipoCalculoInterfaces) {
      this.baseApuracaoTarifaInterfaces = baseApuracaoTarifaInterfaces;
      this.tipoCalculoInterfaces = tipoCalculoInterfaces;
   }

   public void calcularValorTarifa(PontoConsumo pontoConsumo, Long itemFatura, TarifaVigencia tarifaVigencia,
                                   List<TributoAliquota> colecaoTributoAliquota, BigDecimal consumoPeriodo,
                                   PeriodoVO periodo, LocalDate ultimaLeitura) {
      BaseApuracaoTarifaInterface baseApuracaoTarifa = getBaseApuracaoTarifa(tarifaVigencia.getBaseApuracao().getId());
      baseApuracaoTarifa.calcular(tipoCalculoInterfaces, pontoConsumo, itemFatura, tarifaVigencia, colecaoTributoAliquota,
              consumoPeriodo, periodo, ultimaLeitura);
   }

   private BaseApuracaoTarifaInterface getBaseApuracaoTarifa(Long baseApuracao) {
      return this.baseApuracaoTarifaInterfaces.stream().filter(cTarifa -> cTarifa.baseApuracao() == baseApuracao).findFirst().get();
   }
}
