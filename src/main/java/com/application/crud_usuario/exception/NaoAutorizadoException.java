package com.application.crud_usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NaoAutorizadoException extends RuntimeException {
  public NaoAutorizadoException() {
    super("Usuario ou senha inválidos");
  }
}
