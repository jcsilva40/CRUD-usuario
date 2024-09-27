package com.application.crud_usuario.services;

import com.application.crud_usuario.models.Usuario;
import com.application.crud_usuario.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastro(Usuario usuario) {
            verificarEmail(usuario.getEmail());
            String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
            usuarioRepository.save(usuario);
            return usuario;
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscaUsuario(Integer id){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return usuarioOpt;
    }
    public void deletar(Integer id){
        buscaUsuario(id);
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizar(Integer id, Usuario usuario){
        verificarEmail(usuario.getEmail());
        Usuario user = new Usuario(buscaUsuario(id).get());
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        BeanUtils.copyProperties(usuario,user);
        user.setId(id);
        usuarioRepository.save(user);
        return user;
    }

    public Usuario atualizarNome(Integer id, String nome){
        Optional<Usuario> buscaUsuario = usuarioRepository.findById(id);
        buscaUsuario(id);
        buscaUsuario.get().setNome(nome);
        return usuarioRepository.save(buscaUsuario.get());
    }

    public Usuario login(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        if (new BCryptPasswordEncoder().matches(senha,usuarioOpt.get().getSenha())){
            return usuarioOpt.get();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

    }

    public boolean verificarEmail(String email){
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()){
            return false;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }


}
