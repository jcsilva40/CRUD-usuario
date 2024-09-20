package com.example.crud_usuario.controllers;

import com.example.crud_usuario.dtos.LoginResponse;
import com.example.crud_usuario.dtos.UsuarioResponse;
import com.example.crud_usuario.dtos.UsuarioRequest;
import com.example.crud_usuario.dtos.UsuarioUpdateRequest;
import com.example.crud_usuario.models.Usuario;
import com.example.crud_usuario.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> list(){
        List<Usuario> usuarios = usuarioService.list();
        List<UsuarioResponse> responses =new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            responses.add(new UsuarioResponse(usuarios.get(i)));
        }
        return responses.isEmpty() ? noContent().build() : ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Integer id){
        Usuario usuario = usuarioService.findById(id);
        return  ok(new UsuarioResponse(usuario));

    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> register(@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario usuario = new Usuario();
           usuario.setEmail(usuarioRequest.email());
           usuario.setSenha(usuarioRequest.senha());
           usuario.setNome(usuarioRequest.nome());
           usuario.setDataNascimento(usuarioRequest.dataNascimento());
           return ok(new UsuarioResponse(usuarioService.register(usuario)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable Integer id, @RequestBody @Valid UsuarioUpdateRequest usuarioUpdateRequest) {
        return ok(new UsuarioResponse(usuarioService.update(id, usuarioUpdateRequest)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponse> updateNome(@PathVariable Integer id,@RequestParam @Valid String nome){
        return ok(new UsuarioResponse(usuarioService.updateNome(id,nome)));
    }

    //Não usado normalmente é optado por delete lógico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        usuarioService.delete(id);
        return noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam @Valid String email, @RequestParam String senha) {
        return ok(new LoginResponse(usuarioService.login(email, senha)));
    }
}
