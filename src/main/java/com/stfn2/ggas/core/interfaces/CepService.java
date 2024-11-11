package com.stfn2.ggas.core.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Objects;

import com.stfn2.ggas.core.models.Endereco;
import com.stfn2.ggas.core.utils.cep.CepException;
import com.stfn2.ggas.core.utils.cep.CepFormatException;

public interface CepService {
    Endereco buscar(String cep);

    HttpURLConnection getConexao(String cep);

    default void validarCep(String cep) {
        if (Objects.isNull(cep) || cep.isEmpty())
            throw new CepException("O cep informado não pode ser nulo ou vazio");
        if (cep.length() > 8)
            throw new CepFormatException(
                    "CEP fora do formato, caso esteja com hifen, use o metodo de formatacao");
        if (cep.length() < 8)
            throw new CepException("CEP faltando números");
    }

    default String removerMascaraCep(String cep) {
        try {
            validarCep(cep);
            return cep;
        } catch (CepFormatException e) {
            return cep.replace("-", "");
        }
    }

    default String adicionarMarcara(String cep) {
        try {
            validarCep(cep);
            return cep.substring(0, 5) + "-" + cep.substring(5);
        } catch (CepFormatException e) {
            throw new CepException("Cep ja formatado ou fora do padrao");
        }
    }

    default String converteJsonEmString(BufferedReader buffereReader) throws IOException {
        String resposta;
        String jsonString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonString += resposta;
        }
        return jsonString;
    }

    default boolean verificaCepValido(String cep) {
        Endereco endereco = buscar(cep);
        boolean resultadoConsulta = false;

        if (endereco.getCep() != null)
            resultadoConsulta = true;

        return resultadoConsulta;
    }
}
