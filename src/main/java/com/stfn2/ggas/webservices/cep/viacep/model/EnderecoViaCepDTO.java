package com.stfn2.ggas.webservices.cep.viacep.model;

import com.stfn2.ggas.webservices.cep.interfaces.CepInsterfaceDTO;
import lombok.Data;

@Data
public class EnderecoViaCepDTO implements CepInsterfaceDTO {

    private String localidade; // Nome da cidade correspondente ao CEP.
    private String uf; // Sigla do estado correspondente ao CEP.
    private String cep; //O CEP consultado.
    private String logradouro; //Nome da rua ou avenida correspondente ao CEP.
    private String complemento; // Informações adicionais sobre o endereço, como bloco, apartamento, lado ímpar, lado par, etc.
    private String bairro; // Nome do bairro correspondente ao CEP.
    private String gia; //Código GIA (Guia de Informação e Apuração) da cidade.
    private String ibge; //Código IBGE da cidade.
    private String ddd; //Código DDD (Discagem Direta a Distância) da cidade.
    private String siafi; // Código SIAFI (Sistema Integrado de Administração Financeira do Governo Federal) da cidade.

    @Override
    public String getCidade() {
        return localidade;
    }

    @Override
    public void setCidade(String cidade) {
        this.localidade = cidade;
    }
}
