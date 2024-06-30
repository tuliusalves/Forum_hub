package com.tulius.forumHub.dto.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(@NotBlank String email, @NotBlank String senha) {
}
