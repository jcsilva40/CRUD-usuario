package com.stfn2.ggas.services.componentes.calculotributos;

import com.stfn2.ggas.services.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CalculosTributosComponentTest {

   @Mock
   private TributoAliquotaService tributoAliquotaService;

   @Mock
   private  RubricaTributoService rubricaTributoService;

   @Mock
   private  PontoConsumoTributoAliquotaService pontoConsumoTributoAliquotaService;

   @Mock
   private FaturaService faturaService;

   @Mock
   private FaturaItemTributacaoService faturaItemTributacaoService;

   @InjectMocks
   private CalculosTributosComponent calculosTributosComponent = new CalculosTributosComponent(tributoAliquotaService
           , rubricaTributoService, pontoConsumoTributoAliquotaService, faturaService, faturaItemTributacaoService);

   AliquotasVO aliquotas = new AliquotasVO(new BigDecimal(0.12), new BigDecimal(0.076),
           new BigDecimal(0.0165),BigDecimal.ZERO,BigDecimal.ZERO);

   @Test
   void calcularValorFinalComTributos() {
      BigDecimal valor = new BigDecimal(2.5869);

      BigDecimal result = calculosTributosComponent.calcularValorFinalComTributos(valor, aliquotas);

      assertThat(result).isEqualTo(new BigDecimal(3.2393).setScale(4, BigDecimal.ROUND_HALF_UP));
   }

   @Test
   void calcularValorFinalSemTributos() {
      BigDecimal valor = new BigDecimal(3.2393).setScale(4, BigDecimal.ROUND_HALF_UP);

      BigDecimal result = calculosTributosComponent.calcularValorFinalSemTributos(valor, aliquotas);

      assertThat(result).isEqualTo(new BigDecimal(2.5869).setScale(4, BigDecimal.ROUND_HALF_UP));
   }

   @Test
   void obterTributosRubrica() {
   }
}