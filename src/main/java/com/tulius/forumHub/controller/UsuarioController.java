package com.tulius.forumHub.controller;

import com.tulius.forumHub.dto.cursos.DadosCadastroCurso;
import com.tulius.forumHub.dto.usuarios.DadosAtualizacaoUsuario;
import com.tulius.forumHub.dto.usuarios.DadosCadastroUsuario;
import com.tulius.forumHub.dto.usuarios.DadosListagemUsuarios;
import com.tulius.forumHub.service.UsuarioServie;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("usuarios")
@Tag(name="usuario controller", description = "Endpoints das contas dos usuários")
public class UsuarioController {

    @Autowired
    UsuarioServie usuarioServie;

    @Tag(name="Publico Controller", description = "Endpoints Público")
    @Operation(summary = "Crie sua conta", description = "Cria sua conta, use este email e senha no auth para se autenticar")
    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dadosCadastroUsuario,
                                              UriComponentsBuilder uriBuilder){

        return usuarioServie.cadastrarUsuario(dadosCadastroUsuario, uriBuilder);
    }

    @Operation(summary = "Lista todos os usuários cadastrados", description = "Liste todos os usuários cadastrados")
    @GetMapping
    @SecurityRequirement(name="bearer-key")
    public ResponseEntity<Page<DadosListagemUsuarios>> listarTodosUsuario(@PageableDefault(size =10)Pageable paginacao){
        return usuarioServie.listarTodosUsuarios(paginacao);
    }

    @Operation(summary = "Lista um usuário cadastrado.", description = "Liste um usuário cadastrado.")
    @GetMapping("/{id}")
    @SecurityRequirement(name="bearer-key")
    public ResponseEntity<?> listarUsuario(@PathVariable(value="id") UUID id){
        return usuarioServie.listarUsuario(id);
    }

    @Operation(summary = "Atualize informações de um usuário.", description = "Atualize informações de um usuário.")
    @GetMapping("/{id}")
    @Transactional
    @SecurityRequirement(name="bearer-key")
    public ResponseEntity<?> atualizacaoUsuario(@RequestBody @Valid DadosAtualizacaoUsuario dadosAtualizacaoUsuario, @PathVariable(value ="id") UUID id){
        return usuarioServie.atualizarUsuario(dadosAtualizacaoUsuario, id);
    }

    @Operation(summary = "Delete sua conta de usuário.", description = "Delete sua conta de usuário.")
    @GetMapping("/{id}")
    @Transactional
    @SecurityRequirement(name="bearer-key")
    public ResponseEntity<?>cancelarConta(@PathVariable(value ="id")UUID id){
        return usuarioServie.cancelarConta(id);
    }
}
