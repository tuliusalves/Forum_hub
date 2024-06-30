package com.tulius.forumHub.dto.topicos;

import com.tulius.forumHub.models.Status;
import com.tulius.forumHub.models.Topico;
import org.springframework.hateoas.Links;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosListagemTopicos(UUID id, String titulo, String mensagem, LocalDateTime dataCriacao,
                                   Status status, String autor, String curso, Links links) {

    public DadosListagemTopicos(Topico topico){
        this(topico.getId(),topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(),
                topico.getStatus(),topico.getUsuario().getNome(), topico.getCurso().getNome(),
                topico.getLinks());
    }
}
