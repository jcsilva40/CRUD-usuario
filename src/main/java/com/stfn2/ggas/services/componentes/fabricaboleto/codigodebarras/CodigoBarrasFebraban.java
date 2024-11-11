/*
 Copyright (C) <2011> GGAS – Sistema de Gestão Comercial (Billing) de Serviços de Distribuição de Gás

 Este arquivo é parte do GGAS, um sistema de gestão comercial de Serviços de Distribuição de Gás

 Este programa é um software livre; você pode redistribuí-lo e/ou
 modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 publicada pela Free Software Foundation; versão 2 da Licença.

 O GGAS é distribuído na expectativa de ser útil,
 mas SEM QUALQUER GARANTIA; sem mesmo a garantia implícita de
 COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM PARTICULAR.
 Consulte a Licença Pública Geral GNU para obter mais detalhes.

 Você deve ter recebido uma cópia da Licença Pública Geral GNU
 junto com este programa; se não, escreva para Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.


 Copyright (C) 2011-2011 the GGAS – Sistema de Gestão Comercial (Billing) de Serviços de Distribuição de Gás

 This file is part of GGAS, a commercial management system for Gas Distribution Services

 GGAS is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; version 2 of the License.

 GGAS is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
 */

package com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras;

import com.google.common.base.Strings;
import com.stfn2.ggas.core.utils.Log;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados.DadosCodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.uteis.DigitoAutoConferencia;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.uteis.RepresentacaoNumerica;
import com.stfn2.ggas.tools.ToolUtil;

/**
 * Responsável pelo preenchimento dos campos dos {@link CodigoBarras}  especificados pela Febraban. 
 * Em caso de dados incompletos, esta classe preenche o campo em questão do {@link CodigoBarras}
 * com zeros. Para mais detalhes consultar a documentação oficial
 * disponibilizada pela Febraban ou pelos bancos.
 **/
public abstract class CodigoBarrasFebraban implements CodigoBarras {
	
	public static final int TAMANHO_AUTO_CONFERENCIA = 1;
	private static final int TAMANHO_BANCO = 3;
	private static final int TAMANHO_MOEDA = 1;
	private static final int TAMANHO_VENCIMENTO = 4;
	private static final int TAMANHO_VALOR = 10;
	private static final int TAMANHO_CAMPO_LIVRE = 25;	
	private static final String VAZIO = "";
	private static final char ZERO = '0';
	private static final String BANCO_DAYCOVAL = "707";
	private static final String BANCO_ITAU = "341";
	private static final int DIGITO_VERIFICADOR_ZERO = 0;
	private static final String DIGITO_VERIFICADOR_UM = "1";
	private final DadosCodigoBarras dados;

	private Log log = new Log(this.getClass());
	
	/**
	 * Cria um {@link CodigoBarras} que atende os requisitos da Febraban.
	 *
	 * @param dados para o preenchimento dos campos da Febraban
	 */
	CodigoBarrasFebraban(final DadosCodigoBarras dados) {
		this.dados = dados;
	}
	
	/**
	 * Gera o campo livre, que é um espaço no código de barras reservado para
	 * utilização pelos bancos. Como o leiaute desta parte é especificado por
	 * banco, a responsabilidade está delegada para as subclasses.
	 *
	 * @param dados os dados para preenchimento do campo livre
	 * @return uma string contendo o campo livre
	 */
	abstract String campoLivre(final DadosCodigoBarras dados);

	@Override
	public String codigo() {
		StringBuilder construtor = new StringBuilder();		
		construtor.append(encher(!this.dados.banco().equals(BANCO_DAYCOVAL) 
		? this.dados.banco() 
		: BANCO_ITAU , TAMANHO_BANCO));
		construtor.append(encher(this.dados.moeda(), TAMANHO_MOEDA));
		construtor.append(encherDigitoVerificador());
		construtor.append(encher(this.dados.vencimento(), TAMANHO_VENCIMENTO));
		construtor.append(encher(this.dados.valor(), TAMANHO_VALOR));
		construtor.append(encher(campoLivre(this.dados), TAMANHO_CAMPO_LIVRE));
		return construtor.toString();
	}
	
	@Override
	public String representacaoNumerica() {
		return new RepresentacaoNumerica(this).toString();
	}

	private String autoConferencia() {
		return new StringBuilder()
		.append(encher(dados.banco().equals(BANCO_DAYCOVAL) ? dados.banco() : BANCO_ITAU, TAMANHO_BANCO))
		.append(encher(dados.moeda(), TAMANHO_MOEDA))
		.append(encher(dados.vencimento(), TAMANHO_VENCIMENTO))
		.append(encher(dados.valor(), TAMANHO_VALOR))
		.append(encher(campoLivre(this.dados), TAMANHO_CAMPO_LIVRE))
		.toString();
	}

	
	/**
	 * Retorna uma string com os espaços faltando a esquerda da String passada
	 * como argumento preechidos com zeros até que seu tamanho atinga o número
	 * de casas pedido. Um erro pode ocorrer caso a String passada como
	 * argumento seja maior do que o tamanho pedido.
	 *
	 * @param campo a string base
	 * @param casas tamanho objetivo da String
	 * @return a String com o tamanho desejado
	 */
	String encher(String campo, int casas) {
		String valorCampo = ToolUtil.coalescenciaNula(campo, VAZIO);
		if(valorCampo.length() > casas) {
			log.erro("TamanhoCampoExcedido" + valorCampo, " Casas: " + casas);
			return campo;
		} else {
			return Strings.padStart(valorCampo, casas, ZERO);
		}
	}
	
	@Override
	public String toString() {
		return codigo();
	}
	
	/**
	 * Método para verificar o Digito verificador e popular no código de barras
	 * @return a String com a formatação para o código de barras com o digito verificador
	 */
	String encherDigitoVerificador() {
		String digitoVerificadorFormatado = null;
		int digitoVerificador = DigitoAutoConferencia
				.modulo11(autoConferencia());
		
		if(digitoVerificador == DIGITO_VERIFICADOR_ZERO) { 
			digitoVerificadorFormatado = DIGITO_VERIFICADOR_UM; 
		} else {
			digitoVerificadorFormatado = String.valueOf(digitoVerificador);
		}
		
		return encher(digitoVerificadorFormatado, TAMANHO_AUTO_CONFERENCIA);
	}

}
