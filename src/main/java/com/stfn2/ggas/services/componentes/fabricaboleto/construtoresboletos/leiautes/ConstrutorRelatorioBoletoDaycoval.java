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

package com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.leiautes;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.services.componentes.fabricaboleto.construtoresboletos.ConstrutorRelatorioBoleto;

/**
 * A Classe ConstrutorRelatorioBoletoItau.
 */
public class ConstrutorRelatorioBoletoDaycoval extends ConstrutorRelatorioBoleto {

	private static final String MSG_LOCAL_PAGAMENTO = 
			"ATÉ O VENCIMENTO PAGUE PREFERENCIALMENTE NO ITAÚ, APÓS VENCIMENTO SOMENTE NO ITAÚ";

	/**
	 * Instantiates a new construtor relatorio boleto itau.
	 *
	 * @param documentoCobranca the documento cobranca
	 * @throws NegocioException the negocio exception
	 */
	public ConstrutorRelatorioBoletoDaycoval(DocumentoCobranca documentoCobranca) throws NegocioException {
		super(documentoCobranca);
	}

	@Override
	public ConstrutorRelatorioBoleto preencherLocalDePagamento() {
		mapaParametros().put(LOCAL_PAGAMENTO, MSG_LOCAL_PAGAMENTO);
		return this;
	}

}
