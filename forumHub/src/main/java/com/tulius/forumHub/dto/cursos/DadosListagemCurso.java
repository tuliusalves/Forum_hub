package com.tulius.forumHub.dto.cursos;

import com.tulius.forumHub.models.Categoria;
import com.tulius.forumHub.models.Curso;
import org.springframework.hateoas.Links;

import java.util.UUID;

public record DadosListagemCurso(UUID id, String nome, Categoria categoria, Links links) {
    public DadosListagemCurso(Curso curso){

    }
}
