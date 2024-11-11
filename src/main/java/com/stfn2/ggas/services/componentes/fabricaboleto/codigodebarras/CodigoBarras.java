package com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras;

import com.stfn2.ggas.config.exceptions.types.NegocioException;

/**
 * Representa um código de barras de qualquer leiaute disponível em
 * {@link LeiauteCodigoBarras}.
 */
public interface CodigoBarras {
	
	/**
	 * Retorna o código que efetivamente é transformado nas barras do boleto.
	 * Não confundir com representação numérica/linha digitável. Para mais
	 * detalhes consultar a documentação oficial disponibilizada pela Febraban
	 * ou pelos bancos.
	 *
	 * @return o código de barras
	 * @throws NegocioException the negocio exception
	 */
	String codigo() throws NegocioException;
	
	
	/**
	 * Retorna uma representação numérica dos dados deste {@link CodigoBarras}.
	 * A representação numérica/linha digitável exibe algumas seções do
	 * {@link CodigoBarras} organizadas de outra forma.
	 *
	 * @return a representação numérica desta instância.
	 * @throws NegocioException the negocio exception
	 */
	String representacaoNumerica() throws NegocioException;
	
}
