
package com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras;

import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados.DadosCodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.uteis.DigitoAutoConferencia;

/**
 * Implementação do {@link CodigoBarras} que atende o {@link LeiauteCodigoBarras} do Daycoval.
 * Como todas as classes que estendem de {@link CodigoBarrasFebraban}, esta
 * classe não é responsável pelo leiaute inteiro; apenas pelo preenchimento do
 * campo livre. Em caso de dados incompletos, esta classe preenche o campo em
 * questão do {@link CodigoBarras} com zeros. Para mais detalhes consultar a
 * documentação oficial disponibilizada pela Febraban ou pelos bancos.
 **/
public class CodigoBarrasDaycoval extends CodigoBarrasFebraban {

	private static final int TAMANHO_CARTEIRA = 3;
	private static final int TAMANHO_NOSSO_NUMERO = 8;
	private static final int TAMANHO_AGENCIA = 4;
	private static final int TAMANHO_CONTA = 5;
	private static final int TAMANHO_ZEROS = 3;	
	/**

	 * Cria um {@link CodigoBarras} que atende o {@link LeiauteCodigoBarras} do Daycoval.
	 *
	 * @param dados para o preenchimento dos campos da Febraban e do Daycoval
	 */
	CodigoBarrasDaycoval(DadosCodigoBarras dados) {
		super(dados);
	}

	@Override
	String campoLivre(final DadosCodigoBarras dados) {		
		StringBuilder construtor = new StringBuilder();
		construtor.append(encher("109", TAMANHO_CARTEIRA));
		construtor.append(encher(dados.nossoNumero(), TAMANHO_NOSSO_NUMERO));
		construtor.append(encher(DigitoAutoConferencia.modulo10(
						autoConferenciaCampoLivre(dados))
				.toString(), TAMANHO_AUTO_CONFERENCIA));
		construtor.append(encher(dados.agencia(), TAMANHO_AGENCIA));
		construtor.append(encher(dados.conta(), TAMANHO_CONTA));
		construtor.append(encher(DigitoAutoConferencia.modulo10(
						autoConferenciaAgenciaConta(dados))
				.toString(), TAMANHO_AUTO_CONFERENCIA));
		construtor.append(encher(null, TAMANHO_ZEROS));
		return construtor.toString();
	}

	private String autoConferenciaAgenciaConta(final DadosCodigoBarras dados) {
		return new StringBuilder()
				.append(encher(dados.agencia(), TAMANHO_AGENCIA))
				.append(encher(dados.conta(), TAMANHO_CONTA))
				.toString();
	}

	private String autoConferenciaCampoLivre(final DadosCodigoBarras dados) {
		return new StringBuilder()
				.append(encher(dados.agencia(), TAMANHO_AGENCIA))
				.append(encher(dados.conta(), TAMANHO_CONTA))
				.append(encher(dados.carteira(), TAMANHO_CARTEIRA))
				.append(encher(dados.nossoNumero(), TAMANHO_NOSSO_NUMERO))
				.toString();
	}

}
