package com.stfn2.ggas.core.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String numero;

	public Endereco() {
	}

	public Endereco(String cep, String logradouro, String complemento, String bairro, String localidade, String uf,
			String numero) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.numero = numero;
	}
}