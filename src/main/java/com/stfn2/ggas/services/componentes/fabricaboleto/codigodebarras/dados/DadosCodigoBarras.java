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

package com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados;

import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.CodigoBarras;
import com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.FabricaCodigoBarras;

/**
 * Esta interface abstrai um mecanismo de obtenção dos dados necessários para o
 * preenchimento de todos os leiautes de {@link CodigoBarras}
 */
public interface DadosCodigoBarras {
	
	/**
	 * Retorna a chave para que a {@link FabricaCodigoBarras} possa selecionar o leiaute
	 *
	 * @return a chave para seleção do leiaute
	 */
	ChaveLeiauteCodigoBarras chaveLeiaute();

	/**
	 * Retorna o código do {@link Banco} a ser usado no preenchimento do {@link CodigoBarras}.
	 *
	 * @return o código do {@link Banco} para preenchimento no {@link CodigoBarras}
	 */
	String banco();

	/**
	 * Retorna o código da moeda a ser usado no preenchimento do {@link CodigoBarras}.
	 *
	 * @return o código da moeda para preenchimento no {@link CodigoBarras}
	 */
	String moeda();

	/**
	 * Retorna o número do {@link ArrecadadorContratoConvenio} a ser usado no preenchimento do {@link CodigoBarras}.
	 *
	 * @return o número do {@link ArrecadadorContratoConvenio} para preenchimento no {@link CodigoBarras}
	 */
	String convenio();

	/**
	 * Retorna a diferença em dias entre a data de vencimento do {@link CodigoBarras}, e a data base.
	 *
	 * @return a diferença, em dias, para preenchimento no {@link CodigoBarras}
	 */
	String vencimento();

	/**
	 * Retorna o valor monetário, sem caracteres especiais, a ser usado no preenchimento do {@link CodigoBarras}.
	 *
	 * @return o valor monetário para preenchimento no {@link CodigoBarras}
	 */
	String valor();

	/**
	 * Retorna o número da {@link Agencia} a ser usado no preenchimento do {@link CodigoBarras}.
	 *
	 * @return o número da {@link Agencia} para preenchimento no {@link CodigoBarras}
	 */
	String agencia();

	/**
	 * Retorna o número da {@link ContaBancaria} a ser usado no preenchimento do {@link CodigoBarras}.
	 *
	 * @return o número da {@link ContaBancaria} para preenchimento no {@link CodigoBarras}
	 */
	String conta();

	/**
	 * Retorna o número do {@link ArrecadadorCarteiraCobranca} a ser usado no preenchimento do {@link CodigoBarras}.
	 *
	 * @return o número do {@link ArrecadadorCarteiraCobranca} para preenchimento no {@link CodigoBarras}
	 */
	String carteira();

	/**
	 * Retorna o nosso número a ser usado no preenchimento do {@link CodigoBarras}.
	 *
	 * @return o nosso número para preenchimento no {@link CodigoBarras}
	 */
	String nossoNumero();

	/**
	 * Retorna o complemento do nosso número a ser usado no preenchimento do {@link CodigoBarras}.
	 *
	 * @return o complemento do nosso número para preenchimento no {@link CodigoBarras}
	 */
	String complemento();

	/**
	 * Retorna o {@link DocumentoCobranca} a ser usado no preenchimento do {@link CodigoBarras}.
	 *
	 * @return o {@link DocumentoCobranca} para preenchimento no {@link CodigoBarras}
	 */
	DocumentoCobranca documentoCobranca();

	/**
	 * Retorna o {@link ArrecadadorContratoConvenio} a ser usado no preenchimento do {@link CodigoBarras}.
	 *
	 * @return o {@link ArrecadadorContratoConvenio} para preenchimento no {@link CodigoBarras}
	 */
	ArrecadadorContratoConvenio arrecadadorContratoConvenio();
	
}
