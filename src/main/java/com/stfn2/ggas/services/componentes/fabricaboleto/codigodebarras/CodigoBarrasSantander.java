package com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras;


import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados.DadosCodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.uteis.DigitoAutoConferencia;

/**
 *	Classe responsável pelo código de barras Santander 
 *
 */
public class CodigoBarrasSantander extends CodigoBarrasFebraban {

	private static final int TAMANHO_CONTA = 7;
	private static final int TAMANHO_NOSSO_NUMERO = 12;
	private static final int TAMANHO_FIXO = 1;
	private static final int TAMANHO_IOF = 1;
	private static final int TAMANHO_TIPO_MODALIDADE_CARTEIRA = 3;

	/**
	 * Cria um {@link CodigoBarras} que atende o {@link LeiauteCodigoBarras} do
	 * santander.
	 *
	 * @param dados para o preenchimento dos campos da Febraban e da Santander
	 */
	CodigoBarrasSantander(DadosCodigoBarras dados) {
		super(dados);
	}

	@Override
	String campoLivre(DadosCodigoBarras dados) {
		StringBuilder construtor = new StringBuilder();
		construtor.append(encher("9", TAMANHO_FIXO));
		construtor.append(encher(dados.arrecadadorContratoConvenio().getCodigoConvenio(), TAMANHO_CONTA));
		construtor.append(encher(dados.nossoNumero(), TAMANHO_NOSSO_NUMERO));
		construtor.append(
				encher(DigitoAutoConferencia.modulo11(autoConferenciaCampoLivre(dados)).toString(),
						TAMANHO_AUTO_CONFERENCIA));
		construtor.append(encher("0",TAMANHO_IOF));
		construtor.append(encher("101",TAMANHO_TIPO_MODALIDADE_CARTEIRA));
		return construtor.toString();
	}

	private String autoConferenciaCampoLivre(DadosCodigoBarras dados) {
		return new StringBuilder().append(encher(dados.nossoNumero(), TAMANHO_NOSSO_NUMERO)).toString();
	}


}
