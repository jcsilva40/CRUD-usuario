package com.stfn2.ggas.domain.dtos.basic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.stfn2.ggas.core.interfaces.BaseDTO;

import com.stfn2.ggas.domain.IndiceFinanceiroValorNominal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndiceFinanceiroValorNominalBasicDTO extends BaseDTO {
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate dataReferencia;
	Boolean utilizado;
	BigDecimal valor;
	public IndiceFinanceiroValorNominalBasicDTO(IndiceFinanceiroValorNominal entity) {
		super();
	}

	@Override
	public Long getId() {
		return this.id;
	}
}