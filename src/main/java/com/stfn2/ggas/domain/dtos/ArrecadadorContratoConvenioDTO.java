package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.enumTypes.TipoConvenioBancarioEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArrecadadorContratoConvenioDTO extends BaseDTO {
        private Long id;
		private ArrecadadorContrato arrecadadorContrato;
		private TipoConvenioBancarioEnum tipoConvenio;
		private ContaBancaria contaConvenio;
		private ContaBancaria contaCredito;
		private ArrecadadorCarteiraCobranca arrecadadorCarteiraCobranca;
		private String codigoConvenio;
		private Long nsaRemessa;
		private Long nsaRetorno;
		private Long sequenciaCobrancaGerado;
		private Long sequencialCobrancaInicio;
		private Long sequencialCobrancaFim;
		private Boolean indicadorPadrao;
		private DocumentoLayout leiaute;


    	public ArrecadadorContratoConvenioDTO (ArrecadadorContratoConvenio entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}