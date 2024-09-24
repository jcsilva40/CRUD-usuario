package com.application.crud_usuario.services;

import com.application.crud_usuario.models.Usuario;
import com.application.crud_usuario.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastro(Usuario usuario) {
            usuarioRepository.save(usuario);
            return usuario;
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Usuario encontrarPorId(Integer id){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return usuarioOpt.get();
    }

    public void deletar(Integer id){
        if (usuarioRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizar(Integer id, Usuario usuario){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        usuarioOpt.get().setEmail(usuario.getEmail());
        usuarioOpt.get().setSenha(usuario.getSenha());
        usuarioOpt.get().setNome(usuario.getNome());
        usuarioOpt.get().setDataNascimento(usuario.getDataNascimento());
        usuarioRepository.save(usuarioOpt.get());
            return usuarioOpt.get();
    }

    public Usuario atualizarNome(Integer id, String nome){
        Optional<Usuario> buscaUsuario = usuarioRepository.findById(id);
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
