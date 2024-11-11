package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class PontoConsumoMedidorInstalacaoDTO extends BaseDTO {

    private Long medidorId;
    private String medidorDescricao;
    private String pontoConsumoDescricao;
    private String pontoConsumoCil;
    private LocalDate data;


}
