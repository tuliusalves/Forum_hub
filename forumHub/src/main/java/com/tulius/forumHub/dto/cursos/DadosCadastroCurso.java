package com.tulius.forumHub.dto.cursos;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCurso(@NotBlank String nome, @NotBlank String categoria) {
}
