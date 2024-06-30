package com.tulius.forumHub.dto.respostas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosCadastroResposta(@NotBlank String mensagem, @NotNull UUID topicoId) {
}
