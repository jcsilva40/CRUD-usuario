package com.example.crud_usuario.services;

import com.example.crud_usuario.dtos.LoginResponse;
import com.example.crud_usuario.dtos.UsuarioResponse;
import com.example.crud_usuario.dtos.UsuarioRequest;
import com.example.crud_usuario.dtos.UsuarioUpdateRequest;
import com.example.crud_usuario.models.Usuario;
import com.example.crud_usuario.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario register(Usuario usuario) {
        if (usuario.getSenha().length() >= 8 && (
                usuario.getSenha().contains("!") ||
                        usuario.getSenha().contains(".") ||
                        usuario.getSenha().contains("#") ||
                        usuario.getSenha().contains("*") ||
                        usuario.getSenha().contains("%") ||
                        usuario.getSenha().contains("&"))) {
            usuarioRepository.save(usuario);
            return usuario;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public List<Usuario> list(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return usuarioOpt.get();
    }

    public void delete(Integer id){
        if (usuarioRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario update(Integer id, UsuarioUpdateRequest usuarioUpdateRequest){
        Optional<Usuario> usuarioOpt = Optional.ofNullable(findById(id));
        if (usuarioOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (    usuarioUpdateRequest.senha().length()>=8 &&(
                usuarioUpdateRequest.senha().contains("!")||
                usuarioUpdateRequest.senha().contains(".")||
                usuarioUpdateRequest.senha().contains("#")||
                usuarioUpdateRequest.senha().contains("*")||
                usuarioUpdateRequest.senha().contains("%")||
                usuarioUpdateRequest.senha().contains("&"))) {
            usuarioOpt.get().setEmail(usuarioUpdateRequest.email());
            usuarioOpt.get().setSenha(usuarioUpdateRequest.senha());
            usuarioOpt.get().setNome(usuarioUpdateRequest.nome());
            usuarioOpt.get().setDataNascimento(usuarioUpdateRequest.dataNascimento());
            usuarioRepository.save(usuarioOpt.get());
            return usuarioOpt.get();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    }

    public Usuario updateNome(Integer id, String nome){
        Optional<Usuario> buscaUsuario = Optional.ofNullable(findById(id));
        if (buscaUsuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        buscaUsuario.get().setNome(nome);
        return usuarioRepository.save(buscaUsuario.get());
    }


    public Usuario login(String email, String senha) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return usuarioOpt.get();
    }
}
