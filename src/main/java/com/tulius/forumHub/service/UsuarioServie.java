package com.tulius.forumHub.service;

import com.tulius.forumHub.controller.UsuarioController;
import com.tulius.forumHub.dto.status.DadosErros;
import com.tulius.forumHub.dto.status.DadosSucesso;
import com.tulius.forumHub.dto.usuarios.DadosAtualizacaoUsuario;
import com.tulius.forumHub.dto.usuarios.DadosCadastroUsuario;
import com.tulius.forumHub.dto.usuarios.DadosListagemUsuarios;
import com.tulius.forumHub.models.Usuario;
import com.tulius.forumHub.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UsuarioServie {

    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity<?> cadastrarUsuario(DadosCadastroUsuario dadosCadastroUsuario, UriComponentsBuilder uriBuilder){
        UserDetails detailsUsuario= usuarioRepository.findByEmail(dadosCadastroUsuario.email());
        if(!(detailsUsuario == null)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DadosErros("Atenção, esse e-mail já está cadastrado"));
        }
        Usuario usuario = usuarioRepository.save(new Usuario(dadosCadastroUsuario));
        usuario.add(linkTo(methodOn(UsuarioController.class).listarTodosUsuario(Pageable.unpaged())).withRel("Detalhes do usuário"));
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemUsuarios(usuario));
    }

    public ResponseEntity<Page<DadosListagemUsuarios>> listarTodosUsuarios(Pageable paginacao){
        Page<DadosListagemUsuarios> page = usuarioRepository.findAll(paginacao)
                .map(usuario -> {
                    usuario.add(linkTo(methodOn(UsuarioController.class).listarUsuario(usuario.getId())).withRel("Detalhes do usuário"));
                    return new DadosListagemUsuarios(usuario.getId(), usuario.getNome(), usuario.getLinks());
                });
        return ResponseEntity.ok(page);
    }

    public ResponseEntity<?>listarUsuario(UUID id){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if(optionalUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DadosErros("Usuário não encontrado!"));
        }
        Usuario usuario = optionalUsuario.get();
        usuario.add(linkTo(methodOn(UsuarioController.class).listarTodosUsuario(Pageable.unpaged())).withRel("Lista de usuários"));
        return ResponseEntity.ok(new DadosListagemUsuarios(usuario.getId(), usuario.getNome(), usuario.getLinks()));
    }

    public ResponseEntity<?> atualizarUsuario(DadosAtualizacaoUsuario dadosAtualizacaoUsuario, UUID id){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if(optionalUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DadosErros("Usuário não existe no sistema!"));
        }

        Usuario usuario = optionalUsuario.get();
        if(!Usuario.temPermissaoParaModificacao(usuario)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DadosErros("Você não tem permissão para modificar a conta de outra pessoa!"));
        }

        usuario.atualizarInformacoes(dadosAtualizacaoUsuario);
        usuario.add(linkTo(methodOn(UsuarioController.class).listarTodosUsuario(Pageable.unpaged())).withRel("Lista de usuários"));
        return ResponseEntity.ok(new DadosListagemUsuarios(usuario));
    }

    public ResponseEntity<?> cancelarConta(UUID id){
        Optional<Usuario> optionalUsuario= usuarioRepository.findById(id);
        if(optionalUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DadosErros("Conta não encontrado para o cancelamento!"));
        }

        Usuario usuario = optionalUsuario.get();
        if(!Usuario.temPermissaoParaModificacao(usuario)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DadosErros("Você não tem permissão para cancelar a conta de outra pessoa!"));
        }

        usuarioRepository.deleteById(id);
        return ResponseEntity.ok(new DadosSucesso("Conta cancelada com sucesso!"));
    }
}
