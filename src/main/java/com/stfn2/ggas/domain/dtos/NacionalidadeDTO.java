package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Nacionalidade;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NacionalidadeDTO extends BaseDTO {
    private Long id;

    private Boolean estrangeira = false;

    private String descricao;

    public NacionalidadeDTO(Nacionalidade entity) {
        super();
    }

    @Override
    public Long getId() {
        return this.id;
    }
}