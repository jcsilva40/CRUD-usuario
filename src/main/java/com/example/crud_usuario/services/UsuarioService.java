package com.example.crud_usuario.services;

import com.example.crud_usuario.dtos.LoginResponse;
import com.example.crud_usuario.dtos.UsuarioResponse;
import com.example.crud_usuario.dtos.UsuarioRequest;
import com.example.crud_usuario.dtos.UsuarioUpdateRequest;
import com.example.crud_usuario.models.Usuario;
import com.example.crud_usuario.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponse register(UsuarioRequest UsuarioRequest){
        var usuario = new Usuario();
        BeanUtils.copyProperties(UsuarioRequest,usuario);
        usuario.setId(null);
        return new UsuarioResponse(usuarioRepository.save(usuario));
    }

    public List<Usuario> list(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        return usuarioOpt.get();
    }

    public void delete(Integer id){
        if (usuarioRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        usuarioRepository.deleteById(id);
    }

    public UsuarioResponse update(Integer id, UsuarioUpdateRequest usuarioUpdateRequest){
        if (usuarioRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioUpdateRequest,usuario);
        usuario.setId(id);
        return new UsuarioResponse(usuarioRepository.save(usuario));
    }


    public LoginResponse login(String email, String senha) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        }
        return new LoginResponse(usuarioOpt.get());
    }
}