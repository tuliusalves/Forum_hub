package com.tulius.forumHub.dto.cursos;

import com.tulius.forumHub.models.Categoria;
import com.tulius.forumHub.models.Curso;

import java.util.List;
import java.util.UUID;

public record DadosCursos(UUID id, String nome, Categoria categoria, List<Curso>curso) {
}
