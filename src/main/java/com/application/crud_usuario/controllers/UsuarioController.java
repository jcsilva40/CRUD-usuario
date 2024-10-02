package com.application.crud_usuario.controllers;

import com.application.crud_usuario.dtos.*;
import com.application.crud_usuario.mapper.UsuarioMapper;
import com.application.crud_usuario.models.Usuario;
import com.application.crud_usuario.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/usuarios", produces = {"application/json"})
@Tag(name = "CRUD-users", description = "Controle de usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Lista os usuarios cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuarios encontrados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar usuarios"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuario cadastrado")
    })

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar(){
        List<Usuario> usuarios = usuarioService.listar();
     List<UsuarioResponse> responses = usuarios.stream().map(UsuarioMapper::usuarioToUsuarioResponse).toList();
      return responses.isEmpty() ? noContent().build() : ok(responses);
    }

    @Operation(summary = "Procura um usuario especifico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuario encontrado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar usuario"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> encontraPorId(@PathVariable Integer id){
        Usuario usuario = usuarioService.buscaUsuario(id);
        return  ok(UsuarioMapper.usuarioToUsuarioResponse(usuario));

    }

    @Operation(summary = "Realiza criação de usuarios", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastro(@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario usuario = UsuarioMapper.usuarioRequestoToUsuario(usuarioRequest);
        BeanUtils.copyProperties(usuarioRequest,usuario);
           return ok(UsuarioMapper.usuarioToUsuarioResponse(usuarioService.cadastro(usuario)));
    }

    @Operation(summary = "Atualiza um usuarios", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "400", description = "Necessário verificar se body está correto"),
            @ApiResponse(responseCode = "404", description = "Usuario não existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualiza(@PathVariable Integer id, @RequestBody @Valid UsuarioUpdateRequest usuarioUpdateRequest) {
        return ok(UsuarioMapper.usuarioToUsuarioResponse(usuarioService.atualizar(id,UsuarioMapper.usarioUpadateRequestToUsuario(usuarioUpdateRequest))));
    }

    @Operation(summary = "Atualiza o nome de um usuarios", method = "PATCH")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar"),
            @ApiResponse(responseCode = "404", description = "Usuario não existe")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizaNome(@PathVariable Integer id,@RequestParam @Valid String nome){
        return ok(UsuarioMapper.usuarioToUsuarioResponse(usuarioService.atualizarNome(id,nome)));
    }

    @Operation(summary = "Deleta um usuarios", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar"),
            @ApiResponse(responseCode = "404", description = "Usuario não existe")
    })
    //Não usado normalmente é optado por delete lógico
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id){
        usuarioService.deletar(id);
        return noContent().build();
    }

    @Operation(summary = "Faz o login de um usuario", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Login efetuado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao logar"),
            @ApiResponse(responseCode = "401", description = "Usuario não existe")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ok(UsuarioMapper.usuarioToLoginResponse(usuarioService.login(UsuarioMapper.loginRequestTousuario(loginRequest))));
    }
}
