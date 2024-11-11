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

/**
 * Implementação do {@link CodigoBarras} que atende o {@link LeiauteCodigoBarras} do Banco
 * do Brasil, com convênio de 7 posições. Como todas as classes que estendem de
 * {@link CodigoBarrasFebraban}, esta classe não é responsável pelo leiaute
 * inteiro; apenas pelo preenchimento do campo livre. Em caso de dados
 * incompletos, esta classe preenche o campo em questão do {@link CodigoBarras}
 * com zeros. Para mais detalhes consultar a documentação oficial
 * disponibilizada pela Febraban ou pelos bancos.
 */
public class CodigoBarrasBB7Posicoes extends CodigoBarrasFebraban {

	private static final int TAMANHO_ZEROS = 6;
	private static final int TAMANHO_CONVENIO = 7;
	private static final int TAMANHO_COMPLEMENTO = 10;
	private static final int TAMANHO_CARTEIRA = 2;
	
	/**
	 * Cria um {@link CodigoBarras} que atende o {@link LeiauteCodigoBarras} do Banco
	 * do Brasil, com convênio de 7 posições.
	 *
	 * @param dados para o preenchimento dos campos da Febraban e do Banco do Brasil
	 */
	CodigoBarrasBB7Posicoes(DadosCodigoBarras dados) {
		super(dados);
	}

	@Override
	String campoLivre(final DadosCodigoBarras dados) {
		StringBuilder construtor = new StringBuilder();
		construtor.append(encher(null, TAMANHO_ZEROS));
		construtor.append(encher(dados.convenio(), TAMANHO_CONVENIO));
		construtor.append(encher(dados.complemento(), TAMANHO_COMPLEMENTO));
		construtor.append(encher(dados.carteira(), TAMANHO_CARTEIRA));
		return construtor.toString();
	}

}
