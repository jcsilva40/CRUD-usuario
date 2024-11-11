package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO extends BaseDTO {

    private Long cepId;
    
    private String logradouro;
    
    private String cep;

    private String bairro;

    private String localidade;

    private String uf;

    private String numero;

    private String complemento;
    
}

