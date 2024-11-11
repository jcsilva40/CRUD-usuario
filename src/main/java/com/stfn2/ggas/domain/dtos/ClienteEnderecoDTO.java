package com.stfn2.ggas.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ClienteEndereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEnderecoDTO extends BaseDTO {

    private Long cepId;

    @JsonProperty("logradouro")
    private String cepLogradouro;

    @JsonProperty("cep")
    private String cepNumeroCep;

    @JsonProperty("bairro")
    private String cepBairro;

    @JsonProperty("localidade")
    private String cepMunicipioDescricao;

    @JsonProperty("uf")
    private String cepMunicipioUfDescricao;

    private String numero;

    private String complemento;

    private Integer indicadorPrincipal;

    public ClienteEnderecoDTO(ClienteEndereco obj) {
        super();
    }
}

