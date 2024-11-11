package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Arrecadador;
import com.stfn2.ggas.domain.ArrecadadorCarteiraCobranca;
import com.stfn2.ggas.domain.enumTypes.CodigoCarteiraCobrancaEnum;
import com.stfn2.ggas.domain.enumTypes.TipoCarteiraCobrancaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArrecadadorCarteiraCobrancaDTO extends BaseDTO {
	private Long id;
	private Arrecadador arrecadador;
	private CodigoCarteiraCobrancaEnum codigoCarteira;
	private TipoCarteiraCobrancaEnum tipoCarteira;
	private String descricao;
	private Integer numero;
	private Boolean indicadorFaixaNossoNumero;
	private Boolean indicadorNossoNumeroLivre;
	private Boolean indicadorEmissao;
	private Boolean indicadorProtesto;
	private Boolean indicadorEntrega;


	public ArrecadadorCarteiraCobrancaDTO (ArrecadadorCarteiraCobranca entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}