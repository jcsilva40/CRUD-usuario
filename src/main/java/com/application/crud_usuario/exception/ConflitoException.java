package com.application.crud_usuario.exception;

public class ConflitoException extends RuntimeException {
    public ConflitoException(String campo) {
        super(campo+" já cadastrado");
    }
}
