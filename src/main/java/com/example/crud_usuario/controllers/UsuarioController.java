package com.example.crud_usuario.controllers;

import com.example.crud_usuario.dtos.LoginResponse;
import com.example.crud_usuario.dtos.UsuarioResponse;
import com.example.crud_usuario.dtos.UsuarioRequest;
import com.example.crud_usuario.dtos.UsuarioUpdateRequest;
import com.example.crud_usuario.models.Usuario;
import com.example.crud_usuario.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar(){
        List<Usuario> usuarios = usuarioService.listar();
     List<UsuarioResponse> responses = Stream.<UsuarioResponse>builder().build().toList();
      return responses.isEmpty() ? noContent().build() : ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> encontraPorId(@PathVariable Integer id){
        Usuario usuario = usuarioService.encontrarPorId(id);
        return  ok(new UsuarioResponse(usuario));

    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastro(@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioRequest,usuario);
           return ok(new UsuarioResponse(usuarioService.cadastro(usuario)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualiza(@PathVariable Integer id, @RequestBody @Valid Usuario usuario) {
        return ok(new UsuarioResponse(usuarioService.atualizar(id, usuario)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizaNome(@PathVariable Integer id,@RequestParam @Valid String nome){
        return ok(new UsuarioResponse(usuarioService.atualizarNome(id,nome)));
    }

    //Não usado normalmente é optado por delete lógico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        usuarioService.deletar(id);
        return noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam @Valid String email, @RequestParam String senha) {
        return ok(new LoginResponse(usuarioService.login(email, senha)));
    }
}
