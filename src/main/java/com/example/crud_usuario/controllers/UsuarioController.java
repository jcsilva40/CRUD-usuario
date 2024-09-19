package com.example.crud_usuario.controllers;

import com.example.crud_usuario.dtos.LoginResponse;
import com.example.crud_usuario.dtos.UsuarioResponse;
import com.example.crud_usuario.dtos.UsuarioRequest;
import com.example.crud_usuario.dtos.UsuarioUpdateRequest;
import com.example.crud_usuario.models.Usuario;
import com.example.crud_usuario.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> list(){
        List<Usuario> usuarios = usuarioService.list();
        return usuarios.isEmpty() ? noContent().build(): ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id){
        return ok(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> register(@RequestBody @Valid UsuarioRequest usuarioRequest){
        return ok(usuarioService.register(usuarioRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable Integer id, @RequestBody @Valid UsuarioUpdateRequest usuarioUpdateRequest) {
        return ok(usuarioService.update(id, usuarioUpdateRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> updateNome(@PathVariable Integer id,@RequestBody @Valid Usuario usuario){
        return ok(usuarioService.updateNome(id,usuario));
    }

    //Não usado normalmente é optado por delete lógico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        usuarioService.delete(id);
        return noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam @Valid String email, @RequestParam String senha) {
        return ok(usuarioService.login(email, senha));
    }
}
