package com.stfn2.ggas.services.componentes.calculotarifa;

import com.stfn2.ggas.domain.PontoConsumo;
import com.stfn2.ggas.domain.TarifaVigencia;
import com.stfn2.ggas.domain.TributoAliquota;
import com.stfn2.ggas.services.componentes.calculotarifa.vo.DadosVigenciaVO;
import com.stfn2.ggas.services.componentes.calculotarifa.vo.PeriodoVO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BaseApuracaoTarifaInterface {
   DadosVigenciaVO calcular(List<TipoCalculoInterface> tiposCalculo, PontoConsumo pontoConsumo, Long itemFatura,
                            TarifaVigencia tarifaVigencia,
                            List<TributoAliquota> colecaoTributoAliquota, BigDecimal consumoPeriodo,
                            PeriodoVO periodo, LocalDate ultimaLeitura);
   public Long baseApuracao();
   public String descricao();

   default TipoCalculoInterface  getTipoCalculo(List<TipoCalculoInterface> tiposCalculo, Long tipoCalculo){
      return tiposCalculo.stream().filter(tCalculo -> tCalculo.tipoCalculo() == tipoCalculo).findFirst().get();
   }
}
