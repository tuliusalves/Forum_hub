package com.tulius.forumHub.dto.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record DadosAtualizacaoUsuario(String nome, @Email String email,
                                      @Size(min=0, max=15) String senha) {
}
