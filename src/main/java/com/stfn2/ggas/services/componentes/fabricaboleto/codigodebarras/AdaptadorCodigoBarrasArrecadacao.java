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

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados.DadosCodigoBarras;
/**
 * A Class AdaptadorCodigoBarrasArrecadacao.
 */
public class AdaptadorCodigoBarrasArrecadacao implements CodigoBarras {

	private final DadosCodigoBarras dados;

	/** A Constante CODIGO_BARRA_SEM_DIGITOS. */
	private static final String CODIGO_BARRA_SEM_DIGITOS = "codigoBarraSemDigitos";
	
	/** A Constante CODIGO_BARRA_COM_DIGITOS. */
	private static final String CODIGO_BARRA_COM_DIGITOS = "codigoBarraComDigitos";

	/**
	 * Instancia um adaptador codigo barras arrecadacao.
	 *
	 * @param dados os dados
	 */
	public AdaptadorCodigoBarrasArrecadacao(DadosCodigoBarras dados) {
		this.dados = dados;
	}

	@Override
	public String codigo() throws NegocioException {
		return ""; /* ServiceLocator
				.getInstancia()
				.getControladorCobranca()
				.gerarNumeroCodigoBarra(
						this.dados.documentoCobranca(),
						BigDecimal.ZERO)
				.get(CODIGO_BARRA_SEM_DIGITOS).toString();*/
	}

	@Override
	public String representacaoNumerica() throws NegocioException {
		return ""; /*ServiceLocator
				.getInstancia()
				.getControladorCobranca()
				.gerarNumeroCodigoBarra(
						this.dados.documentoCobranca(),
						BigDecimal.ZERO)
				.get(CODIGO_BARRA_COM_DIGITOS).toString();*/
	}

}
