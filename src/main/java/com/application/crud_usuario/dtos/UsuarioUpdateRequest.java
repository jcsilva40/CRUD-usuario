package com.application.crud_usuario.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;
@Builder
public record UsuarioUpdateRequest(
        @Email
        String email,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!#@$%&])(?=.*[0-9])(?=.*[a-z]).{6,15}$")
        String senha,
        @Size(min = 3,max = 120)
        @NotBlank
        String nome,
        @PastOrPresent
        LocalDate dataNascimento
) {
}
