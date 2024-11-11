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
 * Implementação do {@link CodigoBarras} que atende o {@link LeiauteCodigoBarras} da Caixa.
 * Como todas as classes que estendem de {@link CodigoBarrasFebraban}, esta
 * classe não é responsável pelo leiaute inteiro; apenas pelo preenchimento do
 * campo livre. Em caso de dados incompletos, esta classe preenche o campo em
 * questão do {@link CodigoBarras} com zeros. Para mais detalhes consultar a
 * documentação oficial disponibilizada pela Febraban ou pelos bancos.
 */
public class CodigoBarrasCaixa extends CodigoBarrasFebraban {
	
	private static final int TAMANHO_CONTA = 6;
	
	private static final int POSICAO_PRIMEIRA_SEQUENCIA_NOSSO_NUMERO = 0;
	private static final int TAMANHO_NOSSO_NUMERO_PRIMEIRA_SEQUENCIA = 3;
	
	private static final int POSICAO_SEGUNDA_SEQUENCIA_NOSSO_NUMERO = 3;
	private static final int TAMANHO_NOSSO_NUMERO_SEGUNDA_SEQUENCIA = 3;
	
	private static final int POSICAO_TERCEIRA_SEQUENCIA_NOSSO_NUMERO = 6;
	private static final int TAMANHO_NOSSO_NUMERO_TERCEIRA_SEQUENCIA = 9;
	
	private static final int POSICAO_TIPO_DE_COBRANCA = 0;
	private static final int TAMANHO_TIPO_DE_COBRANCA = 1;
	
	private static final int TAMANHO_IDENTIFICADOR_EMISSAO_BOLETO = 1;
	private static final int TAMANHO_NOSSO_NUMERO = 15;
	
	private static final int IDENTIFICADOR_EMISSAO_BOLETO_BENEFICIARIO = 4;
	
	/**
	 * Cria um {@link CodigoBarras} que atende o {@link LeiauteCodigoBarras} da Caixa.
	 *
	 * @param dados para o preenchimento dos campos da Febraban e da Caixa
	 */
	CodigoBarrasCaixa(DadosCodigoBarras dados) {
		super(dados);
	}

	@Override
	String campoLivre(DadosCodigoBarras dados) {
		StringBuilder construtor = new StringBuilder();
		construtor.append(encher(dados.conta(), TAMANHO_CONTA));
		construtor.append(encher(DigitoAutoConferencia.modulo11(
				autoConferenciaConta(dados))
				.toString(), TAMANHO_AUTO_CONFERENCIA));
		construtor.append(encher(primeiraSequenciaNossoNumero(dados), TAMANHO_NOSSO_NUMERO_PRIMEIRA_SEQUENCIA));
		construtor.append(encher(tipoDeCobranca(dados), TAMANHO_TIPO_DE_COBRANCA));
		construtor.append(encher(segundaSequenciaNossoNumero(dados), TAMANHO_NOSSO_NUMERO_SEGUNDA_SEQUENCIA));
		construtor.append(encher(identificadorEmissaoBoleto(), TAMANHO_IDENTIFICADOR_EMISSAO_BOLETO));
		construtor.append(encher(terceiraSequenciaNossoNumero(dados), TAMANHO_NOSSO_NUMERO_TERCEIRA_SEQUENCIA));
		construtor.append(encher(DigitoAutoConferencia.modulo11(
				autoConferenciaCampoLivre(dados))
				.toString(), TAMANHO_AUTO_CONFERENCIA));
		return construtor.toString();
	}
	
	private String primeiraSequenciaNossoNumero(DadosCodigoBarras dados) {
		return encher(dados.nossoNumero(), TAMANHO_NOSSO_NUMERO).substring(POSICAO_PRIMEIRA_SEQUENCIA_NOSSO_NUMERO,
				POSICAO_PRIMEIRA_SEQUENCIA_NOSSO_NUMERO + TAMANHO_NOSSO_NUMERO_PRIMEIRA_SEQUENCIA);
	}
	
	private String segundaSequenciaNossoNumero(DadosCodigoBarras dados) {
		return encher(dados.nossoNumero(), TAMANHO_NOSSO_NUMERO).substring(POSICAO_SEGUNDA_SEQUENCIA_NOSSO_NUMERO,
				POSICAO_SEGUNDA_SEQUENCIA_NOSSO_NUMERO + TAMANHO_NOSSO_NUMERO_SEGUNDA_SEQUENCIA);
	}

	private String terceiraSequenciaNossoNumero(DadosCodigoBarras dados) {
		return encher(dados.nossoNumero(), TAMANHO_NOSSO_NUMERO).substring(POSICAO_TERCEIRA_SEQUENCIA_NOSSO_NUMERO,
				POSICAO_TERCEIRA_SEQUENCIA_NOSSO_NUMERO + TAMANHO_NOSSO_NUMERO_TERCEIRA_SEQUENCIA);
	}

	private String identificadorEmissaoBoleto() {
		return String.valueOf(IDENTIFICADOR_EMISSAO_BOLETO_BENEFICIARIO);
	}

	private String tipoDeCobranca(DadosCodigoBarras dados) {
		return dados.carteira().substring(POSICAO_TIPO_DE_COBRANCA,
				POSICAO_TIPO_DE_COBRANCA + TAMANHO_TIPO_DE_COBRANCA);
	}

	private String autoConferenciaCampoLivre(DadosCodigoBarras dados) {
		return new StringBuilder()
				.append(encher(dados.conta(), TAMANHO_CONTA))
				.append(encher(DigitoAutoConferencia.modulo11(
						autoConferenciaConta(dados))
						.toString(), TAMANHO_AUTO_CONFERENCIA))
				.append(encher(primeiraSequenciaNossoNumero(dados), TAMANHO_NOSSO_NUMERO_PRIMEIRA_SEQUENCIA))
				.append(encher(tipoDeCobranca(dados), TAMANHO_TIPO_DE_COBRANCA))
				.append(encher(segundaSequenciaNossoNumero(dados), TAMANHO_NOSSO_NUMERO_SEGUNDA_SEQUENCIA))
				.append(encher(identificadorEmissaoBoleto(), TAMANHO_IDENTIFICADOR_EMISSAO_BOLETO))
				.append(encher(terceiraSequenciaNossoNumero(dados), TAMANHO_NOSSO_NUMERO_TERCEIRA_SEQUENCIA))
				.toString();
	}

	private String autoConferenciaConta(DadosCodigoBarras dados) {
		return new StringBuilder()
				.append(encher(dados.conta(), TAMANHO_CONTA))
				.toString();
	}

}
