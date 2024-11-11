package com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.utils.ApplicationContextProvider;
import com.stfn2.ggas.core.utils.DadosAuditoriaUtil;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.domain.ArrecadadorContratoConvenio;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.services.ArrecadadorContratoConvenioService;
import com.stfn2.ggas.services.ArrecadadorService;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.CodigoBarras;
import com.stfn2.ggas.tools.ToolDate;
import com.stfn2.ggas.tools.ToolUtil;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Esta classe é responsável por extrair todos os dados necessários para geração
 * de {@link CodigoBarras}, para todos os leiautes. Esta classe não garante
 * tratamentos para informação incompleta.
 */
public class DadosPadraoCodigoBarras implements DadosCodigoBarras {

	private static final String COMPLEMENTO = "";
	private static final char MOEDA = '9';
	private static final LocalDate DATA_BASE_VENCIMENTO = LocalDate.of(1997, 10, 07);
	private final DocumentoCobranca cobranca;
	private final ArrecadadorContratoConvenio convenio;

	private Log log = new Log(this.getClass());

	ApplicationContext context = ApplicationContextProvider.getApplicationContext();
	private final ArrecadadorService arrecadadorService = context.getBean(ArrecadadorService.class);
	private final ArrecadadorContratoConvenioService arrecadadorContratoConvenioService =
			context.getBean(ArrecadadorContratoConvenioService.class);

	/**
	 * Cria uma nova instância desta classe, usando um {@link DocumentoCobranca}
	 * . Esta classe não garante tratamentos para informação incompleta.
	 *
	 * @param cobranca
	 *            o {@link DocumentoCobranca} contendo os dados necessários
	 */
	public DadosPadraoCodigoBarras(DocumentoCobranca cobranca) {
		this.cobranca = cobranca;
     this.convenio = convenioParaBoleto();
	}

	public DadosPadraoCodigoBarras(DocumentoCobranca cobranca, ArrecadadorContratoConvenio convenio) {
     this.cobranca = cobranca;
     this.convenio = convenio;
	}

	@Override
	public ChaveLeiauteCodigoBarras chaveLeiaute() {
		return new ChaveLeiauteCodigoBarras(banco(), this.convenio.getTipoConvenio().getDescricao());
	}

	@Override
	public String banco() {
		return this.convenio.getContaConvenio().getAgencia().getBanco().getCodigoBanco();
	}

	@Override
	public String moeda() {
		return String.valueOf(MOEDA);
	}

	@Override
	public String convenio() {
		return this.convenio.getCodigoConvenio();
	}

	@Override
	public String vencimento() {
		LocalDate vencimento = this.cobranca.getDataVencimento();

		String str = String.valueOf(ToolDate.diasEntre(DATA_BASE_VENCIMENTO, vencimento));
		return str;
	}

	@Override
	public String valor() {
		BigDecimal valor = this.cobranca.getValorTotal();
		return valor.movePointRight(valor.scale()).toString();
	}

	@Override
	public String agencia() {
		return this.convenio.getContaConvenio().getAgencia().getCodigo();
	}

	@Override
	public String conta() {
		return this.convenio.getContaConvenio().getNumeroConta();
	}

	@Override
	public String carteira() {
		return this.convenio.getArrecadadorCarteiraCobranca().getNumero().toString();
	}

	@Override
	public String nossoNumero() {
		Long nossoNumero = this.cobranca.getNossoNumero();
		if (nossoNumero == null) {
			try {
				nossoNumero = sequencialDaFaixa();
			} catch (NegocioException e) {
				log.erro("Convenio Desatualizado", e.getMessage());
			}
		}
		return nossoNumero.toString();
	}

	@Override
	public String complemento() {
		return COMPLEMENTO;
	}

	@Override
	public DocumentoCobranca documentoCobranca() {
		return this.cobranca;
	}

	@Override
	public ArrecadadorContratoConvenio arrecadadorContratoConvenio() {
		return this.convenio;
	}

	private Long sequencialDaFaixa() throws NegocioException {

		return arrecadadorService.calcularNossoNumeroComValidacao(this.convenio,
				DadosAuditoriaUtil.getDadosAuditoria());
	}

	private ArrecadadorContratoConvenio convenioParaBoleto() {
		ArrecadadorContratoConvenio arrecadadorConvenioPadrao = convenioPadrao();
		if (cobranca != null && cobranca.getId() > 0) {
			ArrecadadorContratoConvenio arrecadadorContratoConvenio = arrecadadorContratoConvenioService.obterArrecadadorContratoConvenioPorDocumentoCobranca(cobranca);

			if (arrecadadorContratoConvenio != null) {
				return ToolUtil.coalescenciaNula(arrecadadorContratoConvenio, arrecadadorConvenioPadrao);
			} else {
				return arrecadadorConvenioPadrao;
			}

		}
		return arrecadadorConvenioPadrao;
	}

	private ArrecadadorContratoConvenio convenioPadrao() {
		return arrecadadorContratoConvenioService
				.obterArrecadadorContratoConvenioPadrao();
	}

}
