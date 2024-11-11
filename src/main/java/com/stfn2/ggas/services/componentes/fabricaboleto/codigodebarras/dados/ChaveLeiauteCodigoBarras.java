package com.stfn2.ggas.services.componentes.fabricaboleto.codigodebarras.dados;

/**
 * A Classe ChaveLeiauteCodigoBarras.
 */
public class ChaveLeiauteCodigoBarras {

	private String codigoBanco;

	private String tipoConvenio;
	
	private static final String ARRECADACAO = "Arrecadação";
	
	/**
	 * Instantiates a new chave leiaute codigo barras.
	 *
	 * @param codigoBanco the codigo banco
	 * @param tipoConvenio the tipo convenio
	 */
	public ChaveLeiauteCodigoBarras(String codigoBanco, String tipoConvenio) {
		this.codigoBanco = codigoBanco;
		this.tipoConvenio = tipoConvenio;
	}
	
	public String getCodigoBanco() {
		
		return codigoBanco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoBanco == null) ? 0 : codigoBanco.hashCode());
		result = prime * result + ((tipoConvenio == null) ? 0 : tipoConvenio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ChaveLeiauteCodigoBarras other = (ChaveLeiauteCodigoBarras) obj;
		if (tipoConvenio == null) {
			if (other.tipoConvenio != null) {
				return false;
			}
		} else if (!tipoConvenio.equals(other.tipoConvenio)) {
			return false;
		}
		if (tipoConvenio != null && !tipoConvenio.equals(ARRECADACAO)) {
			if (codigoBanco == null) {
				if (other.codigoBanco != null) {
					return false;
				}
			} else if (!codigoBanco.equals(other.codigoBanco)) {
				return false;
			}
		}
		return true;
	}
	
}
