package com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RelatorioAnaliseFaturamentoDTO implements Serializable {
   private static final long serialVersionUID = 1L;

   private static final Pattern PATTERN = Pattern.compile("\\d+");

   private Long idFatura;
   private Long idFaturaItem;
   private String cliente;
   private String cil;
   private Long cdPontoConsumo;
   private String nomePontoConsumo;
   private String dsRota;
   private String dsGrupoFaturamento;
   private String dsSegmento;
   private LocalDate dataFinal;
   private LocalDate dataVencimento;
   private Integer diasConsumo;
   private BigDecimal consumo;
   private BigDecimal consumoGas;
   private BigDecimal baseICMS;
   private BigDecimal icms;
   private BigDecimal baseICMSSubs;
   private BigDecimal icmsSubstituto;
   private BigDecimal jurosQuantidadeTotal;
   private BigDecimal jurosValorMedio;
   private BigDecimal jurosTotal;
   private BigDecimal multaQuantidadeTotal;
   private BigDecimal multaValorMedio;
   private BigDecimal multaTotal;
   private BigDecimal creditoQuantidadeTotal;
   private BigDecimal creditoValorTotal;
   private BigDecimal debitoQuantidadeTotal;
   private BigDecimal debitoValorTotal;
   private BigDecimal diferimentoTributario;
   private BigDecimal valorTotal;
   private List<SubJurosDTO> listaJuros = new ArrayList<>();
   private List<SubMultaDTO> listaMulta= new ArrayList<>();
   private List<SubGasNaturalDTO> listaGasNatural= new ArrayList<>();
   private List<RubricaDebitoCreditoDTO> listaRubricaDebitoCredito= new ArrayList<>();
   private List<Long> listaRubricaCredito= new ArrayList<>();

   RelatorioAnaliseFaturamentoDTO(Long idFatura, Long idFaturaItem, String cliente, String cil, Long cdPontoConsumo, String nomePontoConsumo, String dsRota,
                                  String dsGrupoFaturamento, String dsSegmento, LocalDate dataFinal, LocalDate dataVencimento, Integer diasConsumo, BigDecimal consumo,
                                  BigDecimal consumoGas, BigDecimal baseICMS, BigDecimal icms, BigDecimal baseICMSSubs, BigDecimal icmsSubstituto, BigDecimal diferimentoTributario,
                                  BigDecimal valorTotal) {
      this.idFatura=idFatura;
      this.idFaturaItem=idFaturaItem;
      this.cliente=cliente;
      this.cil=cil;
      this.cdPontoConsumo=cdPontoConsumo;
      this.nomePontoConsumo=nomePontoConsumo;
      this.dsRota=dsRota;
      this.dsGrupoFaturamento=dsGrupoFaturamento;
      this.dsSegmento=dsSegmento;
      this.dataFinal=dataFinal;
      this.dataVencimento=dataVencimento;
      this.diasConsumo=diasConsumo;
      this.consumo=consumo;
      this.consumoGas=consumoGas;
      this.baseICMS=baseICMS;
      this.icms=icms;
      this.baseICMSSubs=baseICMSSubs;
      this.icmsSubstituto=icmsSubstituto;
      this.diferimentoTributario=diferimentoTributario;
      this.valorTotal=valorTotal;


   }

   /**
    * Método para calcular a Data Inicial
    * @return
    */
   public String getDataInicial() {
        LocalDate dataInicial = dataFinal.minusDays(diasConsumo - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataInicial.format(formatter);
      //return dataF.format(dataInicial);
   }
   
   public String getDataFinal() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataFinal.format(formatter);
   }

   public String getDataVencimento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataVencimento.format(formatter);      
   }

   public BigDecimal getConsumo() {
      if(consumo != null) {
         return consumo.setScale(0, RoundingMode.HALF_EVEN);
      }else {
         return consumo;
      }
   }

   public BigDecimal getValorTotal() {
      setValorTotal();
      return valorTotal;
   }

   /**
    * Método para somar o valor total:
    * consumoGas com Icms Substituto
    */
   public void setValorTotal() {
      this.valorTotal = consumoGas;
   }

   public BigDecimal getBaseICMS() {
      return arredondar(baseICMS, RoundingMode.HALF_EVEN, 2);
   }

    public BigDecimal getBaseICMSSubs() {
      return arredondar(baseICMSSubs, RoundingMode.HALF_EVEN, 2);
   }

   public BigDecimal getIcmsSubstituto() {
      if(icmsSubstituto!= null) {
         return icmsSubstituto;
      }else {
         return BigDecimal.ZERO;
      }
   }

   public BigDecimal getIcms() {
      if(icms!= null) {
         return icms;
      }else {
         return BigDecimal.ZERO;
      }
   }

   public BigDecimal getJurosQuantidadeTotal() {
      return arredondar(jurosQuantidadeTotal, RoundingMode.HALF_EVEN, 4);
   }

   public BigDecimal getJurosValorMedio() {
      return arredondar(jurosValorMedio, RoundingMode.HALF_EVEN, 4);
   }

   public BigDecimal getJurosTotal() {
      return arredondar(jurosTotal, RoundingMode.HALF_EVEN, 2);
   }

   public BigDecimal getMultaQuantidadeTotal() {
      return arredondar(multaQuantidadeTotal, RoundingMode.HALF_EVEN, 4);
   }

   public BigDecimal getMultaValorMedio() {
      return arredondar(multaValorMedio, RoundingMode.HALF_EVEN, 4);
   }

   public BigDecimal getMultaTotal() {
      return arredondar(multaTotal, RoundingMode.HALF_EVEN, 2);
   }

   public BigDecimal getCreditoQuantidadeTotal() {
      return arredondar(creditoQuantidadeTotal, RoundingMode.HALF_EVEN, 4);
   }

   public BigDecimal getCreditoValorTotal() {
      return arredondar(creditoValorTotal, RoundingMode.HALF_EVEN, 2);
   }

   public BigDecimal getDebitoQuantidadeTotal() {
      return arredondar(debitoQuantidadeTotal, RoundingMode.HALF_EVEN, 4);
   }

   public BigDecimal getDebitoValorTotal() {
      return arredondar(debitoValorTotal, RoundingMode.HALF_EVEN, 2);
   }

   public void setListaJuros(List<SubJurosDTO> listaJuros) {
      jurosQuantidadeTotal = BigDecimal.ZERO;
      jurosValorMedio = BigDecimal.ZERO;
      jurosTotal = BigDecimal.ZERO;

      if (listaJuros.size() > 0) {
         jurosQuantidadeTotal = listaJuros.stream().map(SubJurosDTO::getQuantidade) // extrair
                 // os
                 // valores
                 // totais
                 // da
                 // lista
                 .reduce(BigDecimal.ZERO, BigDecimal::add); // somar os
         // valores
         // totais usando
         // o método
         // reduce() da
         // API de
         // Streams

         jurosValorMedio = listaJuros.stream().map(SubJurosDTO::getValorUnitario)
                 .reduce(BigDecimal.ZERO, BigDecimal::add)
                 .divide(BigDecimal.valueOf(listaJuros.size()), 4, RoundingMode.HALF_UP);

         jurosTotal = listaJuros.stream().map(SubJurosDTO::getValorTotal) // extrair
                 // os
                 // valores
                 // totais
                 // da
                 // lista
                 .reduce(BigDecimal.ZERO, BigDecimal::add); // somar os
         // valores
         // totais usando
         // o método
         // reduce() da
         // API de
         // Streams
      }
      this.listaJuros = listaJuros;
   }

   public void setListaMulta(List<SubMultaDTO> listaMulta) {
      multaQuantidadeTotal = BigDecimal.ZERO;
      multaValorMedio = BigDecimal.ZERO;
      multaTotal = BigDecimal.ZERO;

      if (listaMulta.size() > 0) {
         multaQuantidadeTotal = listaMulta.stream().map(SubMultaDTO::getQuantidade) // extrair
                 // os
                 // valores
                 // totais
                 // da
                 // lista
                 .reduce(BigDecimal.ZERO, BigDecimal::add); // somar os
         // valores
         // totais usando
         // o método
         // reduce() da
         // API de
         // Streams

         multaValorMedio = listaMulta.stream().map(SubMultaDTO::getValorUnitario)
                 .reduce(BigDecimal.ZERO, BigDecimal::add)
                 .divide(BigDecimal.valueOf(listaMulta.size()), 4, BigDecimal.ROUND_HALF_UP);

         multaTotal = listaMulta.stream().map(SubMultaDTO::getValorTotal) // extrair
                 // os
                 // valores
                 // totais
                 // da
                 // lista
                 .reduce(BigDecimal.ZERO, BigDecimal::add); // somar os
         // valores
         // totais usando
         // o método
         // reduce() da
         // API de
         // Streams
      }
      this.listaMulta = listaMulta;
   }

   public void setListaRubricaDebitoCredito(List<RubricaDebitoCreditoDTO> listaRubricaDebitoCredito) {
      creditoQuantidadeTotal = BigDecimal.ZERO;
      creditoValorTotal = BigDecimal.ZERO;
      debitoQuantidadeTotal = BigDecimal.ZERO;
      debitoValorTotal = BigDecimal.ZERO;

      if (listaRubricaDebitoCredito != null && listaRubricaDebitoCredito.size() > 0) {
         creditoQuantidadeTotal = listaRubricaDebitoCredito.stream()
                 .filter(rubrica -> listaRubricaCredito.contains(rubrica.getIdRubrica()))
                 .map(RubricaDebitoCreditoDTO::getListaValoresRubricas)
                 .filter(valoresRubricas -> valoresRubricas != null)
                 .flatMap(valoresRubricas -> valoresRubricas.stream())
                 .map(ValoresRubricasDTO::getQuantidade)
                 .reduce(BigDecimal.ZERO, BigDecimal::add);

         creditoValorTotal = listaRubricaDebitoCredito.stream()
                 .filter(rubrica -> listaRubricaCredito.contains(rubrica.getIdRubrica()))
                 .map(RubricaDebitoCreditoDTO::getListaValoresRubricas)
                 .filter(valoresRubricas -> valoresRubricas != null)
                 .flatMap(valoresRubricas -> valoresRubricas.stream())
                 .map(ValoresRubricasDTO::getValorTotal)
                 .reduce(BigDecimal.ZERO, BigDecimal::add);

         debitoQuantidadeTotal = listaRubricaDebitoCredito.stream()
                 .filter(rubrica -> !listaRubricaCredito.contains(rubrica.getIdRubrica()))
                 .map(RubricaDebitoCreditoDTO::getListaValoresRubricas)
                 .filter(valoresRubricas -> valoresRubricas != null)
                 .flatMap(valoresRubricas -> valoresRubricas.stream())
                 .map(ValoresRubricasDTO::getQuantidade)
                 .reduce(BigDecimal.ZERO, BigDecimal::add);

         debitoValorTotal = listaRubricaDebitoCredito.stream()
                 .filter(rubrica -> !listaRubricaCredito.contains(rubrica.getIdRubrica()))
                 .map(RubricaDebitoCreditoDTO::getListaValoresRubricas)
                 .filter(valoresRubricas -> valoresRubricas != null)
                 .flatMap(valoresRubricas -> valoresRubricas.stream())
                 .map(ValoresRubricasDTO::getValorTotal)
                 .reduce(BigDecimal.ZERO, BigDecimal::add);


      }
      this.listaRubricaDebitoCredito = listaRubricaDebitoCredito;
   }

   private BigDecimal arredondar(BigDecimal valor, RoundingMode roundingMode, int casasDecimais) {
      return Optional.ofNullable(valor)
              .map(v -> v.setScale(casasDecimais, roundingMode))
              .orElse(BigDecimal.ZERO);
   }

   public void sortListaGasNatural(){
      listaGasNatural.sort(comparator);
   }

   Comparator<SubGasNaturalDTO> comparator = new Comparator<SubGasNaturalDTO>() {
      @Override
      public int compare(SubGasNaturalDTO sub1, SubGasNaturalDTO sub2) {
         String descricao1 = sub1.getDescricao();
         String descricao2 = sub2.getDescricao();
         Matcher matcher1 = PATTERN.matcher(descricao1);
         Matcher matcher2 = PATTERN.matcher(descricao2);
         if (matcher1.find() && matcher2.find()) {
            int seq1 = Integer.parseInt(matcher1.group());
            int seq2 = Integer.parseInt(matcher2.group());
            return Integer.compare(seq1, seq2);
         }
         return descricao1.compareTo(descricao2);
      }
   };

   @Override
   public String toString() {
      return "RelatorioAnaliseFaturamentoDTO{" +
              "\nidFatura = " + getIdFatura() +
              "\nidFaturaItem = " + getIdFaturaItem() +
              "\ncdPontoConsumo = " + getCdPontoConsumo() +
              "\ndsRota = '" + getDsRota() + '\'' +
              "\ndsGrupoFaturamento = '" + getDsGrupoFaturamento() + '\'' +
              "\ndsSegmento = '" + getDsSegmento() + '\'' +
              "\ndataInicial = '" + getDataInicial() + '\'' +
              "\ndataFinal = " + getDataFinal() +
              "\ndiasConsumo = " + getDiasConsumo() +
              "\nconsumo = " + getConsumo() +
              "\nconsumoGas = " + getConsumoGas() +
              "\nbaseICMS = " + getBaseICMS() +
              "\nicms = " + getIcms() +
              "\nbaseICMSSubs = " + getBaseICMSSubs() +
              "\nicmsSubstituto = " + getIcmsSubstituto() +
              "\njurosTotal = " + getJurosTotal() +
              "\nmultaTotal = " + getMultaTotal() +
              "\nvalorTotal = " + getValorTotal() +
              "\ncreditoQuantidadeTotal = " + getCreditoQuantidadeTotal() +
              "\ncreditoValorTotal = " + getCreditoValorTotal() +
              "\ndebitoQuantidadeTotal = " + getDebitoQuantidadeTotal() +
              "\ndebitoValorTotal = " + getDebitoValorTotal() +
              "\nnomePontoConsumo = '" + getNomePontoConsumo() + '\'' +
              "\nlistaJuros = " + listaJuros +
              "\nlistaMulta = " + listaMulta +
              "\nlistaGasNatural = " + listaGasNatural +
              "\nlistaRubricaDebitoCredito = " + listaRubricaDebitoCredito +
              "\n}";
   }
}
