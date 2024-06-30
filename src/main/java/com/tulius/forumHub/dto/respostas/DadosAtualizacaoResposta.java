package com.tulius.forumHub.dto.respostas;

import com.tulius.forumHub.models.Resposta;
import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoResposta(@NotBlank String mensagem) {
    public DadosAtualizacaoResposta(Resposta resposta){
        this(resposta.getMensagem());
    }
}
