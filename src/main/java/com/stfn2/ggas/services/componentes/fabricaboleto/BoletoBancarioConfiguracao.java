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

package com.stfn2.ggas.services.componentes.fabricaboleto;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.domain.ArrecadadorContratoConvenio;
import com.stfn2.ggas.domain.Banco;
import com.stfn2.ggas.domain.DocumentoCobranca;

import java.util.Date;

/**
 * Interface responsável pela assinatura dos métodos relacionados a configuração do boleto bancario.
 * 
 *
 */
public interface BoletoBancarioConfiguracao {

	String BEAN_ID_BOLETO_BANCARIO_CONFIGURACAO_ITAU = "boletoBancarioConfiguracaoItau";

	String BEAN_ID_BOLETO_BANCARIO_CONFIGURACAO_CAIXA = "boletoBancarioConfiguracaoCaixa";

	/**
	 * Método responsável por gerar a
	 * representação numérica do código de barras.
	 * 
	 * @param codigoBarras
	 *            O número do código de barras.
	 * @return A representação numérica do código
	 *         de barras.
	 */
	String gerarRepresentacaoNumericaCodigoBarras(String codigoBarras);

	/**
	 * Método responsável por gerar o número do
	 * código de barras do boleto bancário.
	 * 
	 * @param banco
	 *            O Banco
	 * @param documentoCobranca
	 *            O documento de cobrança
	 * @param arrecadadorContratoConvenio
	 *            O ArrecadadorContratoConvenio
	 * @param nossoNumero
	 *            O nosso número.
	 * @return O número do código de barras.
	 */
	String gerarCodigoBarras(Banco banco, DocumentoCobranca documentoCobranca, ArrecadadorContratoConvenio arrecadadorContratoConvenio,
													 String nossoNumero);

	/**
	 * Método responsável por calcular o digito
	 * verificador do nosso número.
	 * 
	 * @param agencia
	 *            Número da agência.
	 * @param conta
	 *            Número da conta corrente.
	 * @param carteira
	 *            Código da carteira de cobrança.
	 * @param nossoNumero
	 *            Nosso número.
	 * @return O digito verificador do nosso
	 *         número.
	 */
	String calcularDACNossoNumero(String agencia, String conta, String carteira, String nossoNumero);

	/**
	 * Método responsável por calcular fator de
	 * vencimento de acordo com a data de
	 * vencimento do boleto.
	 * 
	 * @param dataVencimento
	 *            A data de vencimento do boleto.
	 * @return O fator de vencimento.
	 * @throws NegocioException
	 *             Caso ocorra algum erro na
	 *             invocação do método.
	 */
	String calcularFatorVencimento(Date dataVencimento) throws NegocioException;
}
