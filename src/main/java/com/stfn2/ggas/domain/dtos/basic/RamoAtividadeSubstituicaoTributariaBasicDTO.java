package com.stfn2.ggas.domain.dtos.basic;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.RamoAtividadeSubstituicaoTributaria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RamoAtividadeSubstituicaoTributariaBasicDTO extends BaseDTO {
    private Long id;
    private Long tipoSubstituicao;

    public RamoAtividadeSubstituicaoTributariaBasicDTO(RamoAtividadeSubstituicaoTributaria entity) {super(); 	}

    @Override
    public Long getId() {
        return this.id;
    }
}