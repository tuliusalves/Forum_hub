package com.tulius.forumHub.dto.respostas;

import com.tulius.forumHub.models.Resposta;
import org.springframework.hateoas.Links;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosListagemResposta(UUID id, String autor, String mensagem, String topico,
                                    boolean solucao, LocalDateTime dataCriacao, Links links) {

    public DadosListagemResposta(Resposta resposta){
        this(resposta.getId(), resposta.getUsuario().getNome(),
                resposta.getMensagem(), resposta.getTopico().getTitulo(),
                resposta.getSolucao(), resposta.getDataCriacao(),
                resposta.getLinks());
    }
}
