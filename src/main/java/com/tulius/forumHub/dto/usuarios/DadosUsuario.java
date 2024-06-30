package com.tulius.forumHub.dto.usuarios;

import com.tulius.forumHub.models.Topico;

import java.util.List;
import java.util.UUID;

public record DadosUsuario(UUID id, String nome, String email,
                           String senha, List<Topico>topicos) {
}
