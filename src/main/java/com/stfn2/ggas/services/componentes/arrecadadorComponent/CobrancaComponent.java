package com.stfn2.ggas.services.componentes.arrecadadorComponent;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.domain.enumTypes.RecebimentoSituacaoEnum;
import com.stfn2.ggas.services.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CobrancaComponent {

   private static final int TAMANHO_MAX_COD_VALIDACAO = 11;
   private static final int CONSTANTE_NOVECENTOS_NOVENTA_NOVE = 999;
   private static final String ENDERECOS = "enderecos";
   private static final int NUMERO_QUARENTA_QUATRO = 44;
   private static final int NUMERO_TRINTA_TRES = 33;
   private static final int NUMERO_VINTE_DOIS = 22;
   private static final int NUMERO_ZERO = 0;
   private static final String TIPO_PESSOA = "tipo_pessoa";
   private static final String CNPJ_CPF_CLIENTE = "cnpj_cpf_cliente";
   private static final String ENDERECO_EMPRESA = "enderecoEmpresa";
   private static final String IMAGEM_LOGOMARCA_EMPRESA = "imagem";
   private static final int CENTENA = 100;
   private static final int QUATRO_ZEROS = 4;
   private static final int ONZE_ZEROS = 11;
   private static final int LIMITE_DESCRICAO = 2;
   private static final int QUATRO_CASAS_DECIMAIS = 4;
   private static final int DUAS_CASAS_DECIMAIS = 2;
   private static final int TRINTA_DIAS = 30;
   private static final int DEZOITO_CASAS = 18;
   private static final String PROTOCOLOS = "protocolos";
   private static final String RELATORIO_BOLETO_BANCARIO_JASPER = "relatorioBoletoBancario.jasper";
   private static final String RELATORIO_DECLARACAO_QUITACAO_DEBITOS_ANUAL_LOTE_JASPER =
           "relatorioDeclaracaoQuitacaoDebitosAnualLote.jasper";
   private static final String RELATORIO_DECLARACAO_QUITACAO_DEBITOS_ANUAL_NOVO = "relatorioDeclaracaoQuitacaoDebitosAnualNovo.jasper";
   private static final String RELATORIO_DECLARACAO_DEBITOS_EM_ABERTO_LOTE_JASPER = "relatorioDeclaracaoDebitosEmAbertoLote.jasper";
   private static final String TIPO_CLIENTE = "tipoCliente";
   private static final String INSCRICAO_ESTADUAL = "inscricaoEstadual";
   private static final String CODIGO_CLIENTE = "codigoCliente";
   private static final String ENDERECO = "endereco";
   private static final String DOC_IDENTIFICADOR = "docIdentificador";
   private static final String NOME_CLIENTE = "nomeCliente";
   private static final String CODIGO_BARRA = "codigoBarra";
   private static final String NUMERO_CODIGO_BARRA = "numeroCodigoBarra";
   private static final String CODIGO_BARRA_SEM_DIGITOS = "codigoBarraSemDigitos";
   private static final String CODIGO_BARRA_COM_DIGITOS = "codigoBarraComDigitos";
   private static final String VALOR_TOTAL = "valorTotal";
   private static final String TOTAL_CREDITOS = "totalCreditos";
   private static final String CREDITOS = "creditos";
   private static final String TOTAL_DEBITOS = "totalDebitos";
   private static final String DEBITOS = "debitos";
   private static final String FATURANOTASDEBITO = "faturaNotasDebito";
   private static final String NOTASCREDITO = "notasCredito";
   private static final String TOTAL_DOCUMENTOS = "totalDocumentos";
   private static final String DOCUMENTOS = "documentos";
   private static final String RELATORIO_EXTRATO_DEBITO = "relatorioExtratoDebito.jasper";
   private static final String RELATORIO_DECLARACAO_DEBITOS_EM_ABERTO = "relatorioDeclaracaoDebitosEmAberto.jasper";
   private static final String RELATORIO_DECLARACAO_DEBITOS_ANOS_ANTERIORES = "relatorioDeclaracaoDebitosAnosAnteriores.jasper";
   private static final String RELATORIO_DECLARACAO_DEBITOS_ANOS_ANTERIORES_EM_ABERTO = "relatorioDeclaracaoDebitosAnosAnteriores";
   private static final String CODIGO_EMPRESA_FEBRABAN = "CODIGO_EMPRESA_FEBRABAN";
   private static final String NOTA_CREDITO = "DC";
   private static final String CREDITO = "C";
   public static final String COBRANCA_DEBITO_SITUACAO = "cobrancaDebitoSituacao";
   private static final String NOTA_DEBITO_PENALIDADE_REFERENTE = "Nota de Débito de penalidade Referente ao ponto de consumo: ";
   private static final String QNR = "QNR";
   private static final String QRET = "QRET";
   private static final String QREC = "QREC";
   private static final String QUEBRA_LINHA = "\r\n";
   private static final String VALOR_PARAMETRO_APENAS_ARQUIVO_DE_NOTIFICACAO = "2";
   private static final String VALOR_PARAMETRO_ARQUIVO_DE_NOTIFICACAO_E_CONTA = "3";
   private static final String FROM = " from ";
   private static final String WHERE = " where ";

   private final ConstanteSistemaService constanteSistemaService;
   private final DocumentoCobrancaService documentoCobrancaService;
   private final DocumentoImpressaoLayoutService documentoImpressaoLayoutService;
   private final EmpresaService empresaService;
   private final RecebimentoService recebimentoService;

   public CobrancaComponent(ConstanteSistemaService constanteSistemaService,
                            DocumentoCobrancaService documentoCobrancaService,
                            DocumentoImpressaoLayoutService documentoImpressaoLayoutService,
                            EmpresaService empresaService,
                            RecebimentoService recebimentoService
                            ){
      this.constanteSistemaService = constanteSistemaService;
      this.documentoCobrancaService = documentoCobrancaService;
      this.documentoImpressaoLayoutService = documentoImpressaoLayoutService;
      this.empresaService = empresaService;
      this.recebimentoService = recebimentoService;
   }

   public BigDecimal obterValorRecebimentoPelaFatura(Long faturaId) throws NegocioException {

      BigDecimal valor =  BigDecimal.ZERO;
      RecebimentoSituacaoEnum estornado = RecebimentoSituacaoEnum.ESTORNADO;

      valor = recebimentoService.obterValorRecebimentoPelaFatura(faturaId);

      return valor;
   }

}
