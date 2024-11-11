package com.stfn2.ggas.services.componentes.faturamento.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioFaturaVO implements Serializable {
   private static final long serialVersionUID = -4593979344281413040L;

   private String dtEmissao;
   private String dtVencimento;
   private String mesAno;
   private String numeroNotaFiscal;
   private String numeroSerie;
   private String referenciaCiclo;
   private String nomeCDL;
   private String enderecoCDL;
   private String bairroCidadeEstadoCEPCDL;
   private String cnpjCDL;
   private String inscricaoEstadualCDL;
   private String telefone;
   private String url;
   private String dataPostagem;
   private String nomeCliente;
   private String docIdentificador;
   private String enderecoCliente;
   private String bairroCliente;
   private String municipioCliente;
   private String valorAPagar;
   private String codigoCliente;
   private String inscricaoEstadualCliente;
   private String contrato;
   private String ufCliente;
   private String cepCliente;
   private List<SubRelatorioPontoConsumoVO> colecaoPontoConsumo;
   private List<SubRelatorioFaturasVencidasVO> colecaoFaturasVencidas;
   private List<SubRelatorioFornecimentoVO> colecaoFornecimentos;
   private List<SubRelatorioHistoricoConsumoVO> colecaoHistoricoConsumo;
   private List<SubRelatorioProdutosVO> colecaoProdutos;
   private List<SubRelatorioTributosIncluidosVO> colecaoTributosIncluidos;
}