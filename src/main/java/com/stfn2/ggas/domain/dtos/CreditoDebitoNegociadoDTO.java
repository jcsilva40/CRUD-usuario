package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditoDebitoNegociadoDTO extends BaseDTO {
   private Long id;
   private Cliente cliente;
   private CreditoOrigem creditoOrigem;
   private EntidadeConteudo amortizacao;
   private EntidadeConteudo formaCobranca;
   private EntidadeConteudo periodicidadeCobranca;
   private EntidadeConteudo periodicidadeJuros;
   private EntidadeConteudo eventoInicioCobranca;
   private EntidadeConteudo status;
   private FinanciamentoTipo financiamentoTipo;
   private PontoConsumo pontoConsumo;
   /*private Recebimento recebimento;*/
   private Rubrica rubrica;
   private Segmento segmento;
   private User usuario;
   private LocalDateTime dataCobranca;
   private LocalDateTime dataFinanciamento;
   private Integer diaVencimento = 0;
   private String Descricao;
   private Integer diasCarencia = 0;
   private Integer quantidadePrestacaoCobrada = 0;
   private Integer quatidadePrestacoes = 0;
   private BigDecimal percentualJuros = BigDecimal.ZERO;
   private BigDecimal valorEntrada = BigDecimal.ZERO;
   private BigDecimal valor = BigDecimal.ZERO;
   private BigDecimal valorJuros = BigDecimal.ZERO;
   private Integer anoMesInclusao = 0;

   public CreditoDebitoNegociadoDTO(CreditoDebitoNegociado entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}