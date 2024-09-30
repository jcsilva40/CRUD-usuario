package com.application.crud_usuario.services;

import com.application.crud_usuario.exception.ConflitoException;
import com.application.crud_usuario.exception.NaoAutorizadoException;
import com.application.crud_usuario.exception.NaoEncontradaException;
import com.application.crud_usuario.mapper.UsuarioMapper;
import com.application.crud_usuario.models.Usuario;
import com.application.crud_usuario.repositories.UsuarioRepository;
import com.application.crud_usuario.utils.CustomBeanUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
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

    public Usuario buscaUsuario(Integer id){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()){
            throw new NaoEncontradaException("usuario");
        }
        return usuarioOpt.get();
    }

    public void deletar(Integer id){
        buscaUsuario(id);
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizar(Integer id, Usuario usuario){
        verificarEmail(usuario.getEmail());
        Usuario usuarioNovo = buscaUsuario(id);
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        CustomBeanUtils.copyNonNullProperties(usuario,usuarioNovo);
        if (usuarioNovo.getEmail().equals(usuario.getEmail()))usuarioNovo.setEmail(null);
        usuarioNovo.setId(id);
        usuarioRepository.save(usuarioNovo);
        return usuarioNovo;
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
            throw new NaoAutorizadoException();
        }
        if (new BCryptPasswordEncoder().matches(senha,usuarioOpt.get().getSenha())){
            return usuarioOpt.get();
        }
        throw new NaoAutorizadoException();

    }

    private boolean verificarEmail(String email){
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()){
            return false;
        }
        throw new ConflitoException("Email");
    }


}
