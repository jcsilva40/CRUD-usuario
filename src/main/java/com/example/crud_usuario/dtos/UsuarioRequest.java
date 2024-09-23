package com.example.crud_usuario.dtos;

import com.example.crud_usuario.models.Usuario;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UsuarioRequest(
         @NotBlank
         @Email
         String email,
         @Pattern(regexp = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$/")
         @NotBlank
         String senha,
         @Size(min = 3,max = 120)
         @NotBlank
         String nome,
         @PastOrPresent
         LocalDate dataNascimento
) {
    public UsuarioRequest(Usuario usuario) {
        this(   usuario.getEmail(),
                usuario.getSenha(),
                usuario.getNome(),
                usuario.getDataNascimento()
        );
    }
}
