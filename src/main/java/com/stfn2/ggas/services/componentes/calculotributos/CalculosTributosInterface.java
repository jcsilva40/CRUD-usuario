package com.stfn2.ggas.services.componentes.calculotributos;

import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.DadosTributosDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface CalculosTributosInterface {

   default BigDecimal removerTributos(BigDecimal valor, TarifaVigencia tarifaVigencia) {
      return calcularValorFinalSemTributos(valor, obterAliquotasTributos(obterListaTributos(tarifaVigencia)));
   }

   default BigDecimal adicionarTributos(BigDecimal valor, TarifaVigencia tarifaVigencia) {
      return calcularValorFinalComTributos(valor, obterAliquotasTributos(obterListaTributos(tarifaVigencia)));
   }

   default BigDecimal adicionarTributos(BigDecimal valor, Rubrica rubrica) {
      return calcularValorFinalComTributos(valor, obterAliquotasTributos(obterListaTributos(rubrica)));
   }

   default BigDecimal adicionarTributos(BigDecimal valor, List<DadosTributosDTO> retornoTributos) {
      return calcularValorFinalComTributos(valor, obterAliquotasTributos(obterListaTributos(retornoTributos)));
   }

   default BigDecimal adicionarTributos(BigDecimal valor, TarifaVigencia tarifaVigencia, PontoConsumo pontoConsumo){
      AliquotasVO aliquotas = obterAliquotasTributos(obterListaTributos(tarifaVigencia));
      List<PontoConsumoTributoAliquota> diferimentos = verificarDiferimentoPontoConsumo(pontoConsumo);

      if(diferimentos.size() >=1){
         aplicarDiferimentoTributarioNasAliquotas(aliquotas, diferimentos);
      }

      return calcularValorFinalComTributos(valor, aliquotas);
   }

   default List<Tributo> obterListaTributos(TarifaVigencia tarifaVigencia) {
      List<Tributo> tributos = new ArrayList<>();

      for (TarifaVigenciaTributo tvt : tarifaVigencia.getTributos()) {
         tributos.add(tvt.getTributo());
      }
      return tributos;
   }

   default List<Tributo> obterListaTributos(Rubrica rubrica) {
      List<Tributo> tributos = new ArrayList<>();
      List<RubricaTributo> rubricaTributos =  obterTributosRubrica(rubrica);
      for (RubricaTributo rt : rubricaTributos) {
         tributos.add(rt.getTributo());
      }

      return tributos;
   }

   default List<Tributo> obterListaTributos(List<DadosTributosDTO> dadosTributosDTO) {
      List<Tributo> tributos = new ArrayList<>();

      for (DadosTributosDTO dadosTributos : dadosTributosDTO) {
         tributos.add(dadosTributos.getTributoAliquota().getTributo());
      }
      return tributos;
   }

   BigDecimal calcularValorFinalComTributos(BigDecimal valor, AliquotasVO aliquotas);

   BigDecimal calcularValorFinalSemTributos(BigDecimal valor, AliquotasVO aliquotas);

   AliquotasVO obterAliquotasTributos(List<Tributo> tributos);

   List<RubricaTributo> obterTributosRubrica(Rubrica rubrica);

   List<PontoConsumoTributoAliquota> verificarDiferimentoPontoConsumo(PontoConsumo pontoConsumo);

   void aplicarDiferimentoTributarioNasAliquotas(AliquotasVO aliquotas, List<PontoConsumoTributoAliquota> diferimentos);
}

