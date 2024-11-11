package com.stfn2.ggas.core.utils.cep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

import com.stfn2.ggas.core.interfaces.CepService;
import com.stfn2.ggas.core.models.Endereco;

public class ClientViaCep implements CepService {

	public Endereco buscar(String cep) {
		validarCep(cep);
		HttpURLConnection conexao = getConexao(cep);
		Endereco endereco = null;
		BufferedReader buff;
		try {
			buff = new BufferedReader(new InputStreamReader((conexao.getInputStream()), StandardCharsets.UTF_8));
			String convertJsonString = converteJsonEmString(buff);
			Gson gson = new Gson();
			endereco = gson.fromJson(convertJsonString, Endereco.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return endereco;
	}

	public HttpURLConnection getConexao(String cep) {
		String enderecoURL = "https://viacep.com.br/ws/" + cep + "/json/";
		HttpURLConnection conexao = null;
		URL url;
		try {
			url = new URL(enderecoURL);
			conexao = (HttpURLConnection) url.openConnection();
			conexao.setRequestMethod("GET");
			conexao.setDoInput(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conexao;
	}
}
