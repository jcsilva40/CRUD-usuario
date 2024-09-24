package com.application.crud_usuario.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UsuarioUpdateRequest(
        String email,
        String senha,
        String nome,
        LocalDate dataNascimento
) {
}
