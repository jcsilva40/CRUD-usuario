package com.example.crud_usuario.controllers;

import com.example.crud_usuario.dtos.LoginResponse;
import com.example.crud_usuario.dtos.UsuarioResponse;
import com.example.crud_usuario.dtos.UsuarioRequest;
import com.example.crud_usuario.dtos.UsuarioUpdateRequest;
import com.example.crud_usuario.models.Usuario;
import com.example.crud_usuario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        List<Usuario> usuarios = usuarioService.list();
        return usuarios.isEmpty() ? ResponseEntity.status(204).build():ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> register(@RequestBody UsuarioRequest usuarioRequest){
        return ResponseEntity.status(200).body(usuarioService.register(usuarioRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Integer id, @RequestBody UsuarioUpdateRequest usuarioUpdateRequest) {
        return ResponseEntity.status(200).body(usuarioService.update(id, usuarioUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        usuarioService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String email, @RequestParam String senha) {
        return ResponseEntity.status(200).body(usuarioService.login(email, senha));
    }
}
