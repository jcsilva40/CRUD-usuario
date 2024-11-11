package com.stfn2.ggas.constante;

import java.util.Locale;

public final class Constantes {
    
    public static Locale LOCALE_PADRAO = Locale.forLanguageTag("pt-BR");
    public static Integer QUANTIDADE_CASAS_VALOR_DECIMAL = 4;
    public static final String FORMATO_VALOR_NUMERO = "#,###.####";
    public static final String FORMATO_DATA_BR = "dd/MM/yyyy";
    public static final String FORMATO_DATA_HORA_BR = "dd/MM/yyyy HH:mm:ss";
    public static final String RELATORIO_NOTA_FISCAL_FATURA = "RELATORIO_NOTA_FISCAL_FATURA";
    public static final String RELATORIO_VERSO_NOTA_FISCAL_FATURA = "RELATORIO_VERSO_NOTA_FISCAL_FATURA";
    public static final String RELATORIO_COMPLEMENTO_NOTA_FISCAL_FATURA = "RELATORIO_COMPLEMENTO_NOTA_FISCAL_FATURA";
    public static final String ERRO_DOCUMENTO_LAYOUT_NAO_CADASTRADO = "ERRO_DOCUMENTO_LAYOUT_NAO_CADASTRADO";
    public static final String C_TIPO_DOCUMENTO_FATURA = "C_TIPO_DOCUMENTO_FATURA";
    public static final String C_NACIONALIDADE_ESTRANGEIRA = "C_NACIONALIDADE_ESTRANGEIRA";
    public static String URL_LOGOMARCA_GGAS;
    public static final String MASCARA_CNPJ = "##.###.###/####-##";
    public static final String MASCARA_CPF = "###.###.###-##";
    public static final String ERRO_NEGOCIO_BUSCAR_CONSTANTE = "ERRO_NEGOCIO_BUSCAR_CONSTANTE";
    public static final String PARAMETRO_UTILIZAR_MULTIPLOS_CICLOS = "UTILIZAR_MULTIPLOS_CICLOS";
    public static final int QUANTIDADE_MINIMA_DE_DIAS_PARA_SER_CONSIDERADO_MENSAL = 25;
    public static final String CICLO = "ciclo";
    public static final String REFERENCIA = "referencia";
    public static final String ERRO_DADOS_INVALIDOS_SUPERVISORIO = "Dados inválidos";
    public static final String ERRO_DADOS_INVALIDOS = "ERRO_DADOS_INVALIDOS";
    public static final String FORMATO_VALOR_BR = "#,###.##";
    public static final String FORMATO_VALOR_MONETARIO_BR = "#,##0.00";
    public static final String PARAMETRO_TEXTO_SUBSTITUICAO_TRIBUTARIA_NOTA_DESTACADO = "TEXTO_SUBSTITUICAO_TRIBUTARIA_NOTA_DESTACADO";

    public static final String C_RUBRICA_CUSTO_FIXO_MENSAL = "C_RUBRICA_CUSTO_FIXO_MENSAL";
    public static final String C_RUBRICA_ICMS_RETIDO = "C_RUBRICA_ICMS_RETIDO";
    public static final String C_RUBRICA_TERMO_FIXO_MENSAL_ISS = "C_RUBRICA_TERMO_FIXO_MENSAL_ISS";
    public static final String C_RUBRICA_TERMO_FIXO_MENSAL_ICMS = "C_RUBRICA_TERMO_FIXO_MENSAL_ICMS";
    public static final String C_RUBRICA_GAS_ULTRAPASSAGEM_PGU = "C_RUBRICA_GAS_ULTRAPASSAGEM_PGU";
    public static final String C_RUBRICA_GAS_ULTRAPASSAGEM_PGU2 = "C_RUBRICA_GAS_ULTRAPASSAGEM_PGU2";
    public static final String C_RUBRICA_PARCELA_MARGEM = "C_RUBRICA_PARCELA_MARGEM";
    public static final String C_PARCELA_MARGEM_CASCATA = "C_PARCELA_MARGEM_CASCATA";
    public static final String C_SEGMENTOS_CORRIGIDOS = "C_SEGMENTOS_CORRIGIDOS";
    public static final String C_VENCIMENTO_APOS_INICIO_MES = "C_VENCIMENTO_APOS_INICIO_MES";
    public static final String C_RUBRICAS_COMPOEM_ICMS_FATURA = "RUBRICAS_COMPOEM_ICMS_FATURA";
    public static final String C_RUBRICAS_TRIBUTO_IRRF = "C_RUBRICAS_TRIBUTO_IRRF";
    public static final String C_IRRF = "C_IRRF";

    public static final String C_STATUS_FATURA_ENVIADA_CLIENTE = "C_STATUS_FATURA_ENVIADA_CLIENTE";
    public static final String C_STATUS_FATURA_LIBERADA_ENVIO = "C_STATUS_FATURA_LIBERADA_ENVIO";
    public static final String C_STATUS_FATURA_RETORNO_SEFAZ_CANCELADA = "C_STATUS_FATURA_RETORNO_SEFAZ_CANCELADA";
    public static final String C_STATUS_FATURA_RETORNO_SEFAZ_NEGADA = "C_STATUS_FATURA_RETORNO_SEFAZ_NEGADA";
    public static final String C_STATUS_FATURA_RETORNO_SEFAZ_AUTORIZADA = "C_STATUS_FATURA_RETORNO_SEFAZ_AUTORIZADA";
    public static final String C_STATUS_FATURA_PROCESSO_DE_CANCELAMENTO = "C_STATUS_FATURA_PROCESSO_DE_CANCELAMENTO";
    public static final String C_STATUS_FATURA_AGUARDANDO_RETORNO_SEFAZ = "C_STATUS_FATURA_AGUARDANDO_RETORNO_SEFAZ";
    public static final String C_STATUS_FATURA_VALIDADA_LIBERADA = "C_STATUS_FATURA_VALIDADA_LIBERADA";
    public static final String C_STATUS_FATURA_VALIDADA_BLOQUEADA = "C_STATUS_FATURA_VALIDADA_BLOQUEADA";
    public static final String C_STATUS_FATURA_VALIDACAO_PENDENTE = "C_STATUS_FATURA_VALIDACAO_PENDENTE";
    public static final String C_UNIDADE_PRESSAO_FORNECIMENTO = "C_UNIDADE_PRESSAO_FORNECIMENTO";

    public static final String C_PERCENTUAL = "C_PERCENTUAL";
    public static final String C_VALOR = "C_VALOR";
    public static final String C_COFINS = "C_COFINS";
    public static final String C_ICMS = "C_ICMS";
    public static final String C_PIS = "C_PIS";
    public static final String C_IPI = "C_IPI";
    public static final String C_DEBITO = "C_DEBITO";
    public static final String C_CREDITO = "C_CREDITO";
    public static final String AGRUPAR_VOLUMES_IMPRESSAO_FATURA_MAIS_TARIFA_CICLO = "AGRUPAR_VOLUMES_IMPRESSAO_FATURA_MAIS_TARIFA_CICLO";
    public static final String C_RUBRICA_CONSUMO_GAS = "C_RUBRICA_CONSUMO_GAS";
    public static final String C_GAS = "C_GAS";
    public static final String C_CREDITO_DEBITO_CANCELADO = "C_CREDITO_DEBITO_CANCELADO";
    public static final String PARAMETRO_QUANTIDADE_CASAS_DECIMAIS_APRESENTACAO_CONSUMO_APURADO = "QUANTIDADE_CASAS_DECIMAIS_"
            + "APRESENTACAO_CONSUMO_APURADO";
    public static final String ERRO_PARAMETRO_NEGOCIO = "ERRO_PARAMETRO_NEGOCIO";
    public static final int DIVISOR_MODULO10 = 10;
    public static final int DIVISOR_MODULO11 = 11;
    public static final int QTD_CARACTER_ANOMES = 6;
    public static final String STRING_ESPACO = " ";
    public static final String STRING_BARRA = "/";
    public static final String STRING_HIFEN_ESPACO = " - ";
    public static final String STRING_HIFEN = "-";
    public static String URL_LOGOMARCA_BANCO;
    public static final String RELATORIO_BOLETO = "RELATORIO_BOLETO";
    public static final String ERRO_NEGOCIO_BANCO_CODIGO_BARRAS_NAO_IMPLEMENTADO = "ERRO_NEGOCIO_BANCO_CODIGO_BARRAS_NAO_IMPLEMENTADO";
    public static final String MENSAGEM_NOTIFICACAO_CORTE = "MENSAGEM_NOTIFICACAO_CORTE";
    public static final String PARAMETRO_EXIBIR_MESANGEM_CONTA_E_OU_NOTIFICACAO = "COBRANCA_EXIBIR_MESANGEM_CONTA_E_OU_NOTIFICACAO";
    public static final String C_SITUACAO_RECEBIMENTO_PARCIALMENTE_PAGO = "C_SITUACAO_RECEBIMENTO_PARCIALMENTE_PAGO";
    public static final String PARAMETRO_DIRETORIO_ARQUIVOS_SIMULACAO_FATURAMENTO = "DIRETORIO_ARQUIVOS_SIMULACAO_FATURAMENTO";
    
    public static final String C_TIPO_CONVENIO_DEBITO_AUTOMATICO = "C_TIPO_CONVENIO_DEBITO_AUTOMATICO";
    public static final String PARAMETRO_REFERENCIA_FATURAMENTO = "REFERENCIA_FATURAMENTO";
    public static final String C_ATIVIDADE_SISTEMA_CONSISTIR_LEITURA_CONSUMO = "C_ATIVIDADE_SISTEMA_CONSISTIR_LEITURA_CONSUMO";
    public static final String C_ATIVIDADE_SISTEMA_FATURAR_GRUPO = "C_ATIVIDADE_SISTEMA_FATURAR_GRUPO";
    public static final String C_ATIVIDADE_SISTEMA_EMITIR_FATURA = "C_ATIVIDADE_SISTEMA_EMITIR_FATURA";
    public static final String C_ATIVIDADE_SISTEMA_ENCERRAR_CICLO_FATURAMENTO = "C_ATIVIDADE_SISTEMA_ENCERRAR_CICLO_FATURAMENTO";
    public static final String C_ATIVIDADE_SISTEMA_ENCERRAR_REFERENCIA_CONTABIL = "C_ATIVIDADE_SISTEMA_ENCERRAR_REFERENCIA_CONTABIL";
    public static final String C_REFERENCIA_QF_PARADA_PROGRAMADA = "C_REFERENCIA_QF_PARADA_PROGRAMADA";
    public static final String DIRETORIO_ARQUIVOS_FATURA = "DIRETORIO_ARQUIVOS_FATURA";
    public static final String EXTENSAO_ARQUIVO_PDF = ".pdf";
    public static final String C_TIPO_LEITURA_ELETROCORRETOR = "C_TIPO_LEITURA_ELETROCORRETOR";
    public static final String C_TIPO_LEITURA_PLANILHA = "C_TIPO_LEITURA_PLANILHA";
    public static final String C_TIPO_MODALIDADE_USO = "C_TIPO_MODALIDADE_USO";
    public static final int QUANTIDADE_DIAS_PERIODICIDADE = 14;
    public static final int QUANTIDADE_CICLOS_PERIODICIDADE = 3;
    public static final int ULTIMO_DIA_FEVEREIRO = 28;
    public static final int QUANTIDADE_DIAS_PERIODICIDADE_SEMANAL = 7;
    public static final int QUANTIDADE_CICLOS_PERIODICIDADE_SEMANAL = 5;


}
