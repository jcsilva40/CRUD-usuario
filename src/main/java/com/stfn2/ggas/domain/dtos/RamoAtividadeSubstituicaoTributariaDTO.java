package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.RamoAtividadeSubstituicaoTributaria;
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
public class RamoAtividadeSubstituicaoTributariaDTO extends BaseDTO {

    private Long id;
    private Long idRamoAtividade;
    private Long tipoSubstituicao;
    private Long valorSubstituto;
    private BigDecimal percentualSubstituto;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;

    public RamoAtividadeSubstituicaoTributariaDTO(RamoAtividadeSubstituicaoTributaria obj) {super(); 	}

}