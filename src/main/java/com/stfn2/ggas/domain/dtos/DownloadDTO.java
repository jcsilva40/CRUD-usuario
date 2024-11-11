package com.stfn2.ggas.domain.dtos;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class DownloadDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nome;

    private String extensao;

    private byte[] conteudo;

    public DownloadDTO(String nome, String extensao, byte[] conteudo){
        this.nome = nome;
        this.extensao = extensao;
        this.conteudo = conteudo;
    }
}
