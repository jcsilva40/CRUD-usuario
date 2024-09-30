package com.application.crud_usuario.controllers;

import com.application.crud_usuario.dtos.*;
import com.application.crud_usuario.mapper.UsuarioMapper;
import com.application.crud_usuario.models.Usuario;
import com.application.crud_usuario.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
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
    public ResponseEntity<List<UsuarioResponse>> listar(){
        List<Usuario> usuarios = usuarioService.listar();
     List<UsuarioResponse> responses = usuarios.stream().map(UsuarioMapper::usuarioToUsuarioResponse).toList();
      return responses.isEmpty() ? noContent().build() : ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> encontraPorId(@PathVariable Integer id){
        Usuario usuario = usuarioService.buscaUsuario(id);
        return  ok(UsuarioMapper.usuarioToUsuarioResponse(usuario));

    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastro(@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario usuario = UsuarioMapper.usuarioRequestoToUsuario(usuarioRequest);
        BeanUtils.copyProperties(usuarioRequest,usuario);
           return ok(UsuarioMapper.usuarioToUsuarioResponse(usuarioService.cadastro(usuario)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualiza(@PathVariable Integer id, @RequestBody @Valid UsuarioUpdateRequest usuarioUpdateRequest) {
        return ok(UsuarioMapper.usuarioToUsuarioResponse(usuarioService.atualizar(id,UsuarioMapper.usarioUpadateRequestToUsuario(usuarioUpdateRequest))));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizaNome(@PathVariable Integer id,@RequestParam @Valid String nome){
        return ok(UsuarioMapper.usuarioToUsuarioResponse(usuarioService.atualizarNome(id,nome)));
    }

    //Não usado normalmente é optado por delete lógico
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id){
        usuarioService.deletar(id);
        return noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ok(UsuarioMapper.usuarioToLoginResponse(usuarioService.login(UsuarioMapper.loginRequestTousuario(loginRequest))));
    }
}
