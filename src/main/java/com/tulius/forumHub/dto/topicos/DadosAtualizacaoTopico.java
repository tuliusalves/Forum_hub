package com.tulius.forumHub.dto.topicos;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoTopico(@NotBlank String titulo, @NotBlank String mensagem) {
}
