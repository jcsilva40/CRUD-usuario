package com.application.crud_usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoEncontradaException extends RuntimeException {
    public NaoEncontradaException(String objeto) {
        super("O "+objeto+" não foi encontrado");
    }
}
