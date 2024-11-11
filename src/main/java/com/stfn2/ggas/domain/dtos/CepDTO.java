package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CepDTO extends BaseDTO {

    String numeroCep;
    String bairro;
    String logradouro;
    String municipioDescricao;
    String municipioUfSigla;
    Boolean habilitado;

}
