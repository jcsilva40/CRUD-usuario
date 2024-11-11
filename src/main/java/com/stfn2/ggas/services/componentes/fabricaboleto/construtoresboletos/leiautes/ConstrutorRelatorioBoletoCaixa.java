package com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.leiautes;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.ConstrutorRelatorioBoleto;

/**
 * A Classe ConstrutorRelatorioBoletoCaixa.
 */
public class ConstrutorRelatorioBoletoCaixa extends ConstrutorRelatorioBoleto {

	private static final String MSG_LOCAL_PAGAMENTO = "Pagar preferencialmente nas casas lotéricas";

	/**
	 * Instantiates a new construtor relatorio boleto caixa.
	 *
	 * @param documentoCobranca the documento cobranca
	 * @throws NegocioException the negocio exception
	 */
	public ConstrutorRelatorioBoletoCaixa(DocumentoCobranca documentoCobranca) throws NegocioException {
		super(documentoCobranca);
	}

	@Override
	public ConstrutorRelatorioBoleto preencherLocalDePagamento() {
		mapaParametros().put(LOCAL_PAGAMENTO, MSG_LOCAL_PAGAMENTO);
		return this;
	}

}
