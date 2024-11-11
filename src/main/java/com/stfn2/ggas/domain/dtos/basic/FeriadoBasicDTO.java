package com.stfn2.ggas.domain.dtos.basic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.stfn2.ggas.core.interfaces.BaseDTO;

import com.stfn2.ggas.domain.Feriado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeriadoBasicDTO extends BaseDTO {

	private Long id;

	private String descricao;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataFeriado;

	public FeriadoBasicDTO(Feriado entity) {
		super();
	}

	@Override
	public Long getId() {
		return this.id;
	}
}