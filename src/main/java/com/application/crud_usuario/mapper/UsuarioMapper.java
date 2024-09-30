package com.application.crud_usuario.mapper;

import com.application.crud_usuario.dtos.*;
import com.application.crud_usuario.models.Usuario;

public class UsuarioMapper {
    public static Usuario usuarioRequestoToUsuario(UsuarioRequest usuarioRequest){
        return Usuario.builder()
                .email(usuarioRequest.email())
                .senha(usuarioRequest.senha())
                .nome(usuarioRequest.senha())
                .dataNascimento(usuarioRequest.dataNascimento())
                .build();
    }

    public static UsuarioResponse usuarioToUsuarioResponse(Usuario usuario){
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .dataNascimento(usuario.getDataNascimento())
                .build();
    }

    public static Usuario usarioUpadateRequestToUsuario(UsuarioUpdateRequest usuarioUpdateRequest){
        return Usuario.builder()
                .email(usuarioUpdateRequest.email())
                .senha(usuarioUpdateRequest.senha())
                .nome(usuarioUpdateRequest.nome())
                .dataNascimento(usuarioUpdateRequest.dataNascimento())
                .build();
    }

    public static LoginResponse usuarioToLoginResponse(Usuario usuario){
        return LoginResponse.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .build();
    }

    public static Usuario loginRequestTousuario (LoginRequest loginRequest){
        return Usuario.builder()
                .email(loginRequest.email())
                .senha(loginRequest.senha())
                .build();
    }
}
