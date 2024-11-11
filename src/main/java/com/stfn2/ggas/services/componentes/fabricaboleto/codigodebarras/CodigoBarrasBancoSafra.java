package com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras;

import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados.DadosCodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.uteis.DigitoAutoConferencia;

/**
 * Classe CodigoBarrasBancoSafra
 * 
 * @author Stefanini
 */
public class CodigoBarrasBancoSafra extends CodigoBarrasFebraban{

	public static final int TAMANHO_AUTO_CONFERENCIA = 1;
	private static final int TAMANHO_BANCO = 3;
	private static final int TAMANHO_AGENCIA = 5;
	private static final int TAMANHO_NOSSO_NUMERO = 9;
	private static final int TAMANHO_MOEDA = 1;
	private static final int TAMANHO_VENCIMENTO = 4;
	private static final int TAMANHO_VALOR = 10;
	private static final int TAMANHO_CAMPO_LIVRE = 25;	
	private static final int TAMANHO_UM = 1;
	
	private static final String BANCO_SAFRA = "422";
	private static final String DIGITO_SAFRA = "7";
	private static final String TIPO_COBRANCA = "2";

	private DadosCodigoBarras dados;
	
	/**
	 * Cria um {@link CodigoBarras} que atende o {@link LeiauteCodigoBarras} do Banco
	 * do Safra.
	 *
	 * @param dados para o preenchimento dos campos da Febraban e do Banco do Safra
	 */
	CodigoBarrasBancoSafra(DadosCodigoBarras dados) {
		super(dados);
		this.dados = dados;
	}
	
	//codigo de barras
	@Override
	public String codigo() {
		StringBuilder construtor = new StringBuilder();		
		construtor.append(encher(BANCO_SAFRA, TAMANHO_BANCO));
		construtor.append(encher(this.dados.moeda(),TAMANHO_MOEDA));
		construtor.append(DAC());
		construtor.append(encher(this.dados.vencimento(), TAMANHO_VENCIMENTO));
		construtor.append(encher(this.dados.valor(), TAMANHO_VALOR));
		construtor.append(encher(campoLivre(this.dados), TAMANHO_CAMPO_LIVRE));
		return construtor.toString();
	}
	
	@Override
	String campoLivre(final DadosCodigoBarras dados) {
		StringBuilder construtor = new StringBuilder();
		construtor.append(encher(DIGITO_SAFRA, TAMANHO_UM));
		construtor.append(encher(dados.agencia(), TAMANHO_AGENCIA));
		construtor.append(this.dados.arrecadadorContratoConvenio().getContaConvenio().getNumeroConta()+ this.dados.arrecadadorContratoConvenio().getContaConvenio().getNumeroDigito());
		construtor.append(encher(dados.nossoNumero(), TAMANHO_NOSSO_NUMERO));
		construtor.append(encher(TIPO_COBRANCA, TAMANHO_UM));
		return construtor.toString();
	}
		
	private int DAC(){
		StringBuilder codigoBarrasSemDAC = new StringBuilder();		
		codigoBarrasSemDAC.append(encher(BANCO_SAFRA, TAMANHO_BANCO));
		codigoBarrasSemDAC.append(encher(this.dados.moeda(),TAMANHO_MOEDA));
		codigoBarrasSemDAC.append(encher(this.dados.vencimento(), TAMANHO_VENCIMENTO));
		codigoBarrasSemDAC.append(encher(this.dados.valor(), TAMANHO_VALOR));
		codigoBarrasSemDAC.append(encher(campoLivre(this.dados), TAMANHO_CAMPO_LIVRE));
		
		return DigitoAutoConferencia.modulo11(codigoBarrasSemDAC.toString());
	}	
}