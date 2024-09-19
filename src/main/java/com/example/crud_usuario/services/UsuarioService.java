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

    public UsuarioResponse register(UsuarioRequest usuarioRequest) {
        if (usuarioRequest.senha().length() >= 8 && (
                usuarioRequest.senha().contains("!") ||
                        usuarioRequest.senha().contains(".") ||
                        usuarioRequest.senha().contains("#") ||
                        usuarioRequest.senha().contains("*") ||
                        usuarioRequest.senha().contains("%") ||
                        usuarioRequest.senha().contains("&"))) {
            var usuario = new Usuario();
            BeanUtils.copyProperties(usuarioRequest, usuario);
            return new UsuarioResponse(usuarioRepository.save(usuario));
        }
        // Validar
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

    public UsuarioResponse update(Integer id, UsuarioUpdateRequest usuarioUpdateRequest){
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
            return new UsuarioResponse(usuarioRepository.save(usuarioOpt.get()));
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    }

    public Usuario updateNome(Integer id, Usuario usuario){
        Optional<Usuario> buscaUsuario = Optional.ofNullable(findById(id));
        if (buscaUsuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        buscaUsuario.get().setNome(usuario.getNome());
        return usuarioRepository.save(buscaUsuario.get());
    }


    public LoginResponse login(String email, String senha) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return new LoginResponse(usuarioOpt.get());
    }
}
