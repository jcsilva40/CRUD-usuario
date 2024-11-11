package com.stfn2.ggas.services.componentes.faturamento.uteis;

import com.stfn2.ggas.domain.PontoConsumo;

import java.math.BigDecimal;
import java.util.Map;

public class ConstantesFatura {

   public static final int RETORNO_MAXIMO_DOCUMENTO_FISCAL = 12;
   public static final int LIMITE_RETORNO_CONSULTA = 4;
   public static final int FATOR_MULTIPLICADOR_ANO_CONTRATO = 100000;
   public static final String MOTIVO_REVISAO = "motivoRevisao";
   public static final String CREDITO_DEBITO_A_REALIZAR = "creditoDebitoARealizar";
   public static final String RESPONSAVEL_REVISAO = "responsavelRevisao";
   public static final String FATURA_AVULSO = "faturaAvulso";
   public static final String PERIODO_CONSUMO = "periodoConsumo";
   public static final String VALOR_ICMS1 = "Valor ICMS";
   public static final String VOLUME_FATURADO = "Volume Faturado";

   public static final int CHAVE_ATIVIDADE_SISTEMA = 9;

   public static final int QUATRO_CASAS_DECIMAIS = 4;

   public static final int DUAS_CASAS_DECIMAIS = 2;

   public static final int LIMITE_HISTORICO_GRAFICO = 12;

   public static final int PERCENTUAL = 100;

   public static final int TAMANHO_MAXIMO_LISTA = 1000;

   public static final int LIMITE_CAMPO = 2;

   public static final int NUMERO_DECIMAIS = 2;

   public static final int LIMITE_CAMPO_OBRIGATORIO = 2;

   public static final String CASCATA_TARIFARIA = "CASCTA";

   public static final String CHAVE_ITEM_FATURA = "chaveItemFatura";

   public static final int INDEX_NUMERO_CICLO = 1;

   public static final int INDEX_ANO_MES_FATURAMENTO = 0;

   public static final String ID_TIPO_OPERACAO_ENTRADA = "idTipoOperacaoEntrada";

   public static final String IDS_SEGMENTO = "idsSegmento";

   public static final String FATURA = "fatura";

   public static final String NUMERO_FATURA = "numero";

   public static final String ID_CREDITO_DEBITO_SITUACAO = "idCreditoDebitoSituacao";

   public static final String SITUACAO_VALIDA = "situacaoValida";

   public static final String HABILITADO = "habilitado";

   public static final String COBRADA = "cobrada";

   public static final String ID_RAMO_ATIVIDADE = "idRamoAtividade";

   public static final String HISTORICO_CONSUMO = "historicoConsumo";

   public static final String TIPO_SEGMENTO = "segmento";

   public static final String BATCH_EXECUTADO_COM_SUCESSO = "\r\n Batch executado com sucesso! \r\n\r\n";

   public static final String ID_GRUPO_FATURAMENTO = "idGrupoFaturamento";

   public static final String ANO_MES_FATURAMENTO = "anoMesFaturamento";

   public static final String ANO_MES_FATURAMENTO_FORMULARIO = "anoMesFaturamentoFormulario";

   public static final String QUADRA_FACE = "quadraFace";

   public static final String ENDERECOS = "enderecos";

   public static final String GRUPO_FATURAMENTO = "grupoFaturamento";

   public static final String REFERENCIA_FATURA = "referencia";

   public static final String CICLO = "ciclo";

   public static final String IMAGEM = "imagem";

   public static final String CHAVE_ROTA = "chaveRota";

   public static final String NATUREZA_OPERACAO_CFOP = "naturezaOperacaoCFOP";

   public static final String DESCONTO_FATURA = "Desconto";

   public static final String VALOR_BASE_CALCULO_ICMS = "valorBaseCalculoICMS";

   public static final String VALOR_ICMS = "valorICMS";

   public static final String INSCRICAO_ESTADUAL_CDL = "inscricaoEstadualCDL";

   public static final String DATA_LEITURA_ANTERIOR = "dataLeituraAnterior";

   public static final String SERIE_TIPO = "serie";

   public static final String CPJ_CNPJ = "cpjCnpj";

   public static final String NOME_CLIENTE = "nomeCliente";

   public static final String ENDERECO_CLIENTE = "enderecoCliente";

   public static final String VALOR_UNITARIO = "Valor Unitário";

   public static final String QUANTIDADE_ITENS = "quantidade";

   public static final String VALOR_PIS = "Valor PIS";

   public static final String ALIQUOTA = "Alíquota ";

   public static final String SITUACAO_DE_PAGAMENTO = "Situação de Pagamento";

   public static final String NATUREZA_OPERACAO_DESC = "naturezaOperacaoDesc";

   public static final String DOC_IDENTIFICADOR = "docIdentificador";

   public static final String PESSOA_FISICA_CONTROLE_FLUXO = "pessoaFisicaControleFluxo";

   public static final String CODIGO_CLIENTE = "codigoCliente";

   public static final String INDICADOR_BOLETO = "indicadorBoleto";

   public static final String LANCAMENTO_ITEM_CONTABIL = "lancamentoItemContabil";

   public static final String FINANCIAMENTO_TIPO = "financiamentoTipo";

   public static final String ITEM_FATURA = "itemFatura";

   public static final String EVENTO_COMERCIAL_INCLUIR_FATURA_DESCONTO_CONDICIONADO = "EVENTO_COMERCIAL_INCLUIR_FATURA_DESCONTO_CONDICIONADO#";

   public static final String EVENTO_COMERCIAL_CANCELAR_FATURA_DESCONTO_CONDICIONADO = "EVENTO_COMERCIAL_CANCELAR_FATURA_DESCONTO_CONDICIONADO#";

   public static final String EVENTO_COMERCIAL_DEVOLVER_FATURA_DESCONTO_CONDICIONADO = "EVENTO_COMERCIAL_DEVOLVER_FATURA_DESCONTO_CONDICIONADO#";

   public static final String EVENTO_COMERCIAL = "EVENTO_COMERCIAL_";

   public static final String SEQUENCIA_NUMERACAO = "sequenciaNumeracao";

   public static final String STATUS = "status";

   public static final String DATA_INICIO_COBRANCA = "dataInicioCobranca";

   public static final String INDICADOR_COMPOSICAO_NOTA_FISCAL = "indicadorComposicaoNotaFiscal";

   public static final String INDICADOR_CLIENTE = "indicadorCliente";

   public static final String FATURA_COM_IMOVEL_SEM_DESCRICAO = "Fatura com imóvel sem descrição";

   public static final String FATURA_SEM_IMOVEL = "Fatura sem imóvel";

   public static final int CASAS_VALOR_MONETARIO = 2;

   public static final String NUMERO_NOTA_FISCAL_FINAL = "Final";

   public static final String NUMERO_NOTA_FISCAL_INICIAL = "numeroNotaFiscalInicial";

   public static final String NUMERO_DOCUMENTO_FINAL = "numeroDocumentoFinal";

   public static final String NUMERO_DOCUMENTO_INICIAL = "numeroDocumentoInicial";

   public static final String SITUACAO_DEBITO = "situacaoDebito";

   public static final String DATA_EMISSAO_FINAL = "dataEmissaoFinal";

   public static final String DATA_EMISSAO_INICIAL = "dataEmissaoInicial";

   public static final String DATA_VENCIMENTO_INICIAL = "dataVencimentoInicial";

   public static final String DATA_VENCIMENTO_FINAL = "dataVencimentoFinal";

   public static final String UF_CLIENTE = "ufCliente";

   public static final String CEP_CLIENTE = "cepCliente";

   public static final String CODIGO_BARRA_CEP = "codigoBarraCEP";

   public static final String CONTRATO = "contrato";

   public static final String SETOR_COMERCIAL = "setorComercial";

   public static final String IMOVEL = "imovel";

   public static final String ID_CONTRATO = "idContrato";

   public static final String FATURA_AGRUPADA = "faturaAgrupada";

   public static final String ID_ROTA = "idRota";

   public static final String CLIENTE = "cliente";

   public static final String CONSUMO_DE_GAS = "Consumo de Gás";

   public static final String PONTO_VIRGULA = ";";

   public static final String SEPARADOR = " # ";

   public static final String CHAVE_FATURA = "chaveFatura";

   public static final String SITUACAO_PAGAMENTO = "situacaoPagamento";

   public static final String DATA_EMISSAO = "dataEmissao";

   public static final String CHAVES_PONTO_CONSUMO = "chavesPontoConsumo";

   public static final String ARRAY_SITUACAO = "arraySituacao";

   public static final String CHAVES_FATURA = "chavesFatura";

   public static final String VALOR_MINIMO_GERACAO_DOCUMENTO_COBRANCA = "Valor mínimo para geração do documento de cobrança";

   public static final String DESCRICAO_MOTIVO_CANCELAMENTO_MULTA_RECISORIA = "Refaturamento de nota de débito de multa recisória.";

   public static final String ERRO_ARQUIVO_FATURA_INACESSIVEL = "ERRO_ARQUIVO_FATURA_INACESSIVEL";

   public static final String ID_TIPO_DOCUMENTO = "idTipoDocumento";

   public static final String UNCHECKED = "unchecked";

   public static final String RELATORIO_RESUMO_LOTE = "relatorioResumoLote.jasper";

   public static final String RELATORIO_RESUMO_FATURA_RUBRICA = "relatorioResumoFaturaRubrica.jasper";

   public static final String DESCRICAO_MOTIVO_REVISAO = "descricaoMotivoRevisao";

   public static final String CREDITO_DEBITO_SITUACAO = "creditoDebitoSituacao";

   public static final String CREDITOS_DEBITO_SITUACAO = "creditosDebitoSituacao";

   public static final String TIPO_DOCUMENTO = "tipoDocumento";

   public static final String PONTO_CONSUMO = "pontoConsumo";

   public static final String CHAVE_PRIMARIA = "chavePrimaria";

   public static final String ARRAY_PAGAMENTO = "arrayPagamento";

   public static final String RELATORIO_NOTA_DEBITO = "relatorioNotaDebito.jasper";

   public static final String INCIDEM_JUROS_MULTA = "incidemJurosMulta";

   public static final String CHAVES_PRIMARIAS = "chavesPrimarias";

   public static final String DATA_VENCIMENTO = "dataVencimento";

   public static final String MOTIVO_ALTERACAO = "motivoAlteracao";

   public static final String PERIODO_EMISSAO_FATURAMENTO_MAIOR_DATA_ATUAL = "PERIODO_EMISSAO_FATURAMENTO_MAIOR_DATA_ATUAL";

   public static final String PERIODO_FINAL_EMISSAO_FATURAMENTO_MAIOR_DATA_INICIAL = "PERIODO_FINAL_EMISSAO_FATURAMENTO_MAIOR_DATA_INICIAL";

   public static final String SITUACAO_ALTERACAO_VENCIMENTO_INVALIDO = "SITUACAO_ALTERACAO_VENCIMENTO_INVALIDO";

   public static final String ERRO_NEGOCIO_SITUACAO_CANCELAR_FATURA = "ERRO_NEGOCIO_SITUACAO_CANCELAR_FATURA";

   public static final String ERRO_NEGOCIO_DATA_EMISSAO_INFERIOR_PERMITIDA = "ERRO_NEGOCIO_DATA_EMISSAO_INFERIOR_PERMITIDA";

   public static final String ERRO_SELECAO_MOTIVO_CANCELAMENTO = "ERRO_SELECAO_MOTIVO_CANCELAMENTO";

   public static final String CODIGO_BARRA_COM_DIGITOS = "codigoBarraComDigitos";

   public static final String ERRO_NEGOCIO_CANCELAR_FATURA_JA_SOLICITADA = "ERRO_NEGOCIO_CANCELAR_FATURA_JA_SOLICITADA";

   public static final String TEXTO_SUBSTITUICAO_TRIBUTARIA_NOTA = "TEXTO_SUBSTITUICAO_TRIBUTARIA_NOTA";

   public static final String EMPRESA = "Empresa";

   public static final String LABEL_CLIENTE = "Cliente";

   public static final String RELATORIO_NOTA_FISCAL_FATURA_COM_LAYOUT = "relatorioFaturaNotaFiscalComLayout.jasper";

   public static final String RELATORIO_DANFE_COMPAGAS = "relatorioDanfeCompagas.jrxml";

   public static final String RELATORIO_BOLETO_BANCARIO_PADRAO = "relatorioBoletoBancarioPadrao.jasper";

   public static final String RELATORIO_BOLETO_BANCARIO_PADRAO_DAYCOVAL = "relatorioBoletoBancarioPadraoDaycoval.jasper";

   public static final String ERRO_REFATURAR_FATURA_FATURAMENTO_POSTERIOR = "ERRO_REFATURAR_FATURA_FATURAMENTO_POSTERIOR";

   public static final String ID_CLIENTE = "idCliente";

   public static final String ID_PONTO_CONSUMO = "idPontoConsumo";

   public static final String ID_SEGMENTO = "idSegmento";

   public static final String RELATORIO_RESUMO_FATURAR_GRUPO_RUBRICA_TIPOCOBRANCA = "relatorioResumoFaturarGrupo.jasper";

   public static final String RELATORIO_RESUMO_FATURAR_GRUPO_ANOMALIAS = "relatorioAnomaliasFaturarGrupo.jasper";

   public static final String NUMERO = "Número";

   public static final String SERIE = "Série";

   public static final String MOTIVO = "Motivo";

   public static final String DATA_DE_EMISSAO = "Data de emissão";

   public static final String CHAVE_ACESSO = "Chave de acesso";

   public static final String NUMERO_PROTOCOLO = "Número do protocolo";

   public static final String DESCONTO_GRUPO_ECONOMICO = "Desconto Grupo Economico";

   public static final String TIPO_SUBSTITUTO = "TIPO_SUBSTITUTO";

   public static final String ID_TRIBUTO = "idTributo";

   public static final String ID_MUNICIPIO = "idMunicipio";

   public static final String IDS_CONTRATO = "idsContrato";

   public static final String CONTRATOS_ATIVOS = "contratosAtivos";

   public static final String ID_PERIDIOCIDADE = "idPeriodicidade";

   public static final String ERRO_NEGOCIO_INCLUIR_NFD_STATUS_NFE = "ERRO_NEGOCIO_INCLUIR_NFD_STATUS_NFE";

   public static final String ERRO_NEGOCIO_INCLUIR_NFD_FATURAS_QUITADAS = "ERRO_NEGOCIO_INCLUIR_NFD_FATURAS_QUITADAS";

   public static final String ERRO_NEGOCIO_INCLUIR_NFD_FATURAS_NOTA_ENTRADA = "ERRO_NEGOCIO_INCLUIR_NFD_FATURAS_NOTA_ENTRADA";

   public static final String IDS_SEGMENTOS = "idsSegmentos";

   public static final String DATA_REFERENCIA = "camposDataReferencia";

   public static final String ID_IMOVEL = "idImovel";

   public static final String SIM = "1";

   public static final long CODIGO_ENCO_DEBITO_AUTOMATICO = 577L;

   public static final String ARRAY_TIPO_DOCUMENTO = "arrayTipoDocumento";
   public static final String RESUMO_DE_IMPRESSAO_DAS_FATURAS = "Resumo de impressão das faturas";
   public static final String TODAS_AS_FATURAS = "Todas as Faturas";
   public static final String SEPARADOR_ARQUIVO = " - ";

   public static final String MENSAGEM_ANORMALIDADE_LEITURA = "mensagemAnormalidadeLeitura";
   public static final String DATA_ATUAL = "dataAtual";
   public static final int MAX_RESULTS_FATURAS_EM_ABERTO = 3;
   public static final String ROTA_RN = "ROTA: \r\n";
   public static final String ID_FATURA = "idFatura";
   public static final int NUMERO_DECIMAIS_RELATORIO_DEMME = 4;

   public static boolean IS_ICMS_ISENTO_DESTACADO = Boolean.FALSE;
   public static final String MENSAGEM_ANORMALIDADE_CONSUMO = "mensagemAnormalidadeConsumo";

   public BigDecimal percentualAliquota;

   public BigDecimal valorAliquota;

   public static final int QUANTIDADE_MAXIMA_EM_TELA = 8;

   public Map<PontoConsumo, BigDecimal> mapaDescontoGrupoEconomico;

   public static final String RELATORIO_DESCONTOS_CONCEDIDOS_FATURA = "relatorioDescontosConcedidosFatura.jasper";

   public static String VIRGULA_ESPACO = ", ";

   public static final String ANO_MES_REFERENCIA = "anoMesReferencia";

   public static final String SEGMENTO_CHAVE_PRIMARIA = "segmento.chavePrimaria";

   public static final String FATURA_SUB_CONSULTA = "faturaSubConsulta";

   public static final String FATURA_SUB_CONSULTA_SEGMENTO_ID = "faturaSubConsulta.segmento.chavePrimaria";

   public static final String FATURA_SUB_CONSULTA_FATURA_AGRUPADA_ID = "faturaSubConsulta.faturaAgrupada.chavePrimaria";

   public static final String PONTO_CONSUMO_CHAVE_PRIMARIA = "pontoConsumo.chavePrimaria";

   public static final String TIPO_DOCUMENTO_CHAVE_PRIMARIA = "tipoDocumento.chavePrimaria";

   public static final String FATURA_SUB_CONSULTA_PONTO_CONSUMO_ID = "faturaSubConsulta.pontoConsumo.chavePrimaria";

   public static final String FATURA_CHAVE_PRIMARIA = "fatura.chavePrimaria";

   public static final String BARRA_INVERTIDA = "\\";

   public static final int VALOR_INCREMENTO = 1;

   public static final String ERRO_NEGOCIO_RELATORIO_SEM_DADOS = "ERRO_NEGOCIO_RELATORIO_SEM_DADOS";

   public static final String ERRO_NEGOCIO_FATURA_SERIE_COM_NUMERO_INVALIDO = "ERRO_NEGOCIO_FATURA_SERIE_COM_NUMERO_INVALIDO";

   public static final String ERRO_NEGOCIO_O_CONTRATO_NAO_POSSUI_FATOR_CORRECAO = "ERRO_NEGOCIO_O_CONTRATO_NAO_POSSUI_FATOR_CORRECAO";

   public static final String RELATORIO_FATURAMENTO = "relatorioAnaliseFaturamento.jasper";

   public static String SELECT = " select ";

   public static String WHERE = " where ";

   public static String FROM = " from ";

   public static final String LISTA_FATURA_ITEM = "listaFaturaItem";

   public static final String DATA_REVISAO = "dataRevisao";

   public static final String ID_SITUACAO = "idSituacao";

   public static final String CLIENTE_CHAVE_PRIMARIA = "cliente.chavePrimaria";

   public static final String ROTA_GRUPO_FATURAMENTO = "rota.grupoFaturamento";

   public static final String CONTRATO_ATUAL = "contratoAtual";

   public static final String FATURA_PRINCIPAL = "faturaPrincipal";

   public static final String SITUACAO_PAGAMENTO_CHAVE_PRIMARIA = "situacaoPagamento.chavePrimaria";

   public static final String ID_FATURA_AGRUPADA = "idFaturaAgrupada";

   public static final String HISTORICO_MEDICAO = "historicoMedicao";

   public static final String COLECAO_PAGINADA = "colecaoPaginada";

   public static final String CLIENTE_NOME = "cliente.nome";

   public static final String PONTO_CONSUMO_DESCRICAO = "pontoConsumo.descricao";

   public static final String SEGMENTO_DESCRICAO = "segmento.descricao";

   public static final String VALOR_TOTAL = "valorTotal";

   public static final String SEGMENTO = "segmento";

   public static final int CENTENA = 100;

   public static final String VALOR_PAGAR = "valorAPagar";

   public static final String VALOR_PARAMETRO_APENAS_CONTA = "1";

   public static final String VALOR_PARAMETRO_ARQUIVO_DE_NOTIFICACAO_E_CONTA = "3";

}
