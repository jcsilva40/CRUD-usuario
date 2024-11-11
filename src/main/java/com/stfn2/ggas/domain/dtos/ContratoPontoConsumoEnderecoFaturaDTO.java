package com.stfn2.ggas.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContratoPontoConsumoEnderecoFaturaDTO extends BaseDTO {
    
    private Long id;
    
    @JsonProperty("logradouro")
    private String logradouro;
    
    @JsonProperty("cep")
    private String cepFatura;

    @JsonProperty("bairro")
    private String bairroFatura;

    @JsonProperty("localidade")
    private String municipioFatura;

    @JsonProperty("uf")
    private String ufFatura;

    @JsonProperty("numero")
    private String numeroImovelFatura;

    @JsonProperty("complemento")
    private String complementoFatura;    
}

