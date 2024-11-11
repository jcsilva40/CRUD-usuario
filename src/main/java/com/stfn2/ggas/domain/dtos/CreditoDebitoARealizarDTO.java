package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.enumTypes.CreditoDebitoSituacaoEnum;
import com.stfn2.ggas.domain.enumTypes.PeriodicidadeJurosEnum;
import com.stfn2.ggas.domain.enumTypes.TipoPeriodicidadeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditoDebitoARealizarDTO extends BaseDTO {

    //informações para editar credito debito
   private Long id;
   private CreditoDebitoSituacaoEnum creditoDebitoSituacao;
   private Integer anoMesCobranca;
   private BigDecimal valor = BigDecimal.ZERO;
   private String periodicidade;

   private Boolean cancelar = false;
   private String tipoCobrancaRubrDesc;
   private Boolean executada;
   private Integer numeroPrestCobrada = 0;
   private Boolean reativar=false;

   //informaçõs tanto para criar quanto para editar cobrança já existente
   private String credDebNegComplemento; // complemento/descrição
    private Long pontoConsumoId;
    private String pontoConsumoDescricao; //descricao da rubrica
    private String pontoConsumoCil;
    private Integer ciclo = 0;
    private Integer anoMesFaturamento = 0;


    //informações para criar credito debito a realizar
    // Informações do cliente
    private String endereco;
    private Long clienteId;
    private String cpfCnpj;

    // Informações financeiras
    private Integer numeroParcelas;
    private Long rubricaId;
    private BigDecimal quantidade;
    private BigDecimal valorUnitario = BigDecimal.ZERO;;
    private BigDecimal valorTotal = BigDecimal.ZERO;;
    private BigDecimal valorEntrada = BigDecimal.ZERO;;
    private BigDecimal taxaJuros = BigDecimal.ZERO;;
    private Integer melhorDiaVencimento;
    private LocalDateTime dataInicioCobranca;

    // Periodicidade e indicador
    private TipoPeriodicidadeEnum periodicidadeCobranca;
    private PeriodicidadeJurosEnum periodicidadeJuros;
    private String indicadorCreditoDebito;


   public CreditoDebitoARealizarDTO(CreditoDebitoARealizar entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}