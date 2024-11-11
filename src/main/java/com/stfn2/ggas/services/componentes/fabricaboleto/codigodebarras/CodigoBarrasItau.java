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

import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados.DadosCodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.uteis.DigitoAutoConferencia;

/**
 * Implementação do {@link CodigoBarras} que atende o {@link LeiauteCodigoBarras} do Itaú.
 * Como todas as classes que estendem de {@link CodigoBarrasFebraban}, esta
 * classe não é responsável pelo leiaute inteiro; apenas pelo preenchimento do
 * campo livre. Em caso de dados incompletos, esta classe preenche o campo em
 * questão do {@link CodigoBarras} com zeros. Para mais detalhes consultar a
 * documentação oficial disponibilizada pela Febraban ou pelos bancos.
 **/
public class CodigoBarrasItau extends CodigoBarrasFebraban {

	private static final int TAMANHO_CARTEIRA = 3;
	private static final int TAMANHO_NOSSO_NUMERO = 8;
	private static final int TAMANHO_AGENCIA = 4;
	private static final int TAMANHO_CONTA = 5;
	private static final int TAMANHO_ZEROS = 3;
	
	/**
	 * Cria um {@link CodigoBarras} que atende o {@link LeiauteCodigoBarras} do Itaú.
	 *
	 * @param dados para o preenchimento dos campos da Febraban e do Itaú
	 */
	CodigoBarrasItau(DadosCodigoBarras dados) {

		super(dados);
	}

	@Override
	String campoLivre(final DadosCodigoBarras dados) {
		StringBuilder construtor = new StringBuilder();
		construtor.append(encher(dados.carteira(), TAMANHO_CARTEIRA));
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
