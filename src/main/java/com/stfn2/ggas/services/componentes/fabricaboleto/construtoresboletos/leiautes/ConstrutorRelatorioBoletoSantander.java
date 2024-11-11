package com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.leiautes;


import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.services.ConstanteSistemaService;
import com.stfn2.ggas.services.ParametroSistemaService;
import com.stfn2.ggas.services.componentes.fabricaboleto.FabricaConstrutorBoleto;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.ConstrutorRelatorioBoleto;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.uteis.DigitoAutoConferencia;
import com.stfn2.ggas.tools.ToolDate;
import com.stfn2.ggas.tools.ToolStr;
import com.stfn2.ggas.tools.ToolUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * A Classe ConstrutorRelatorioBoletoSantander.
 */
public class ConstrutorRelatorioBoletoSantander extends ConstrutorRelatorioBoleto {

	private static final int TAMANHO_MAX_NOSSO_NUMERO = 8;
	private static final int TRINTA_DIAS = 30;
	private static final String MSG_LOCAL_PAGAMENTO = "Págavel em qualquer Banco até o vencimento";
	private static final String AGENCIA_CODIGO = "agenciaCodigo";
	private ArrecadadorContratoConvenio arrecadador;
	private ContaBancaria conta;

	private Log log = new Log(this.getClass());

	private static final String VALOR_PARAMETRO_APENAS_CONTA = "1";
	private static final String VALOR_PARAMETRO_ARQUIVO_DE_NOTIFICACAO_E_CONTA = "3";

	private ParametroSistemaService parametroSistemaService = new ParametroSistemaService();
	private ConstanteSistemaService constanteSistemaService = new ConstanteSistemaService();

	/**
	 * Instantiates a new construtor relatorio boleto santander.
	 *
	 * @param documentoCobranca the documento cobranca
	 * @throws NegocioException the negocio exception
	 */
	public ConstrutorRelatorioBoletoSantander(DocumentoCobranca documentoCobranca) throws NegocioException {
		super(documentoCobranca);
	}

	@Override
	public ConstrutorRelatorioBoleto preencherLocalDePagamento() {

		mapaParametros().put(LOCAL_PAGAMENTO, MSG_LOCAL_PAGAMENTO);
		mapaParametros().put("msgDebitoAutomatico", obterMensagemBoleto());
		return this;
	}

	@Override
	public ConstrutorRelatorioBoleto preencherNossoNumero() {
		this.arrecadador = FabricaConstrutorBoleto.convenioParaBoleto(documentoCobranca);
		this.conta = this.arrecadador.getContaConvenio();
		Integer codigoCarteira = arrecadador.getArrecadadorCarteiraCobranca().getNumero();
		String dacNossoNumero = String
				.valueOf(DigitoAutoConferencia.modulo11(documentoCobranca.getNossoNumero().toString()));
		mapaParametros().put(NOSSO_NUMERO,
				codigoCarteira + Constantes.STRING_BARRA
						+ ToolStr.adicionarZerosEsquerdaNumero(documentoCobranca.getNossoNumero().toString(),
								TAMANHO_MAX_NOSSO_NUMERO)
						+ Constantes.STRING_HIFEN + dacNossoNumero);
		return this;
	}

	@Override
	public ConstrutorRelatorioBoleto preencherAgenciaCodigo() {
		this.arrecadador = FabricaConstrutorBoleto.convenioParaBoleto(documentoCobranca);
		this.conta = this.arrecadador.getContaConvenio();
		mapaParametros().put(AGENCIA_CODIGO, conta.getAgencia().getCodigo() + Constantes.STRING_BARRA
				+ this.arrecadador.getCodigoConvenio() + Constantes.STRING_HIFEN + conta.getNumeroDigito());
		return this;
	}

	/**
	 * Obter Mensagem do Boleto Santander
	 * 
	 * @return StrinBuilder - link {@link StringBuilder}
	 * @throws NegocioException
	 */
	public StringBuilder obterMensagemBoleto() {
		StringBuilder mensagemBoleto = new StringBuilder();
		Contrato contrato = obterContratoDocumentoCobranca(documentoCobranca);
		Fatura fatura = obterFatura(documentoCobranca);
		String mensagemParametro = "";
		try {
			mensagemBoleto.append("\n Multa de R$ ");
			mensagemBoleto.append(obterValorJuros(contrato, documentoCobranca.getValorTotal()));
			mensagemBoleto.append(" APÓS ");
			mensagemBoleto
					.append(ToolDate.converterDataParaString(documentoCobranca.getDataVencimento(), Boolean.FALSE));
			mensagemBoleto.append("\n JUROS DE R$ ");
			mensagemBoleto.append(obterValorMoraPorDia(contrato, documentoCobranca.getValorTotal()));
			mensagemBoleto.append(" AO DIA");
			mensagemBoleto.append("\n");
			mensagemBoleto.append("\n NÃO RECEBER APÓS 120 DIAS DE ATRASO");
			mensagemBoleto.append("\n APÓS VENCIMENTO COBRAR JUROS BANCÁRIOS");

			ParametroSistema parametroExibirMensagemContaEOuNotificacao = parametroSistemaService
					.obterParametroPorCodigo(Constantes.PARAMETRO_EXIBIR_MESANGEM_CONTA_E_OU_NOTIFICACAO);

			if (parametroExibirMensagemContaEOuNotificacao != null
					&& verificarSeDeveExibirMensagemNoArquivoDeNotificacaoDeCorte(
							parametroExibirMensagemContaEOuNotificacao)
					&& verificarSeTemIndicadorNotificacaoFatura(fatura)) {
				mensagemParametro = parametroSistemaService
						.obterParametroPorCodigo(Constantes.MENSAGEM_NOTIFICACAO_CORTE).getValor();
				mensagemBoleto.append("\n");
				mensagemBoleto.append(mensagemParametro);
			}

		} catch (Exception e) {
			log.erro(e.getMessage(), e.getCause().getMessage());
		}
		return mensagemBoleto;
	}

	private static Contrato obterContratoDocumentoCobranca(DocumentoCobranca documentoCobranca) {
		if (!ToolUtil.isNullOrEmpty(documentoCobranca.getItens())) {
			FaturaGeral faturaGeral = documentoCobranca.getItens().iterator().next().getFaturaGeral();

			if (faturaGeral != null) {
				return documentoCobranca.getItens().iterator().next().getFaturaGeral().getFaturaAtual()
						.getContratoAtual();
			}
		}

		return null;
	}

	private static String obterValorMoraPorDia(Contrato contrato, BigDecimal valorTitulo) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.DOWN);

		if (contrato != null && contrato.getPercentualJurosMora() != null) {
			Double valorMora = contrato.getPercentualJurosMora().doubleValue();
			Double valorTotalTitulo = valorTitulo.doubleValue();
			Double valorTotalMora = (valorTotalTitulo * valorMora) / TRINTA_DIAS;

			return df.format(valorTotalMora);
		}

		return "0,00";
	}

	private static String obterValorJuros(Contrato contrato, BigDecimal valorTitutlo) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.DOWN);
		if (contrato != null && contrato.getIndicadorMulta()) {
			Double valorTituloJuros = contrato.getPercentualMulta().doubleValue() * valorTitutlo.doubleValue();
			return df.format(valorTituloJuros);
		} else {
			return "0.00";
		}
	}

	private static Fatura obterFatura(DocumentoCobranca documentoCobranca) {
		if (!ToolUtil.isNullOrEmpty(documentoCobranca.getItens())) {
			FaturaGeral faturaGeral = documentoCobranca.getItens().iterator().next().getFaturaGeral();

			if (faturaGeral != null) {
				return faturaGeral.getFaturaAtual();
			}
		}

		return null;
	}

	private boolean verificarSeDeveExibirMensagemNoArquivoDeNotificacaoDeCorte(ParametroSistema parametroSistema) {
		return VALOR_PARAMETRO_APENAS_CONTA.equals(parametroSistema.getValor())
				|| VALOR_PARAMETRO_ARQUIVO_DE_NOTIFICACAO_E_CONTA.equals(parametroSistema.getValor());
	}

	private boolean verificarSeTemIndicadorNotificacaoFatura(Fatura fatura) {
		return fatura != null && fatura.getNotificacao() != null
				&& fatura.getNotificacao();
	}
}
