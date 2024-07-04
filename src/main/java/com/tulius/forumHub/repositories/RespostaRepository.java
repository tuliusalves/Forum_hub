package com.tulius.forumHub.repositories;

import com.tulius.forumHub.models.Resposta;
import com.tulius.forumHub.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, UUID> {
    @Query("SELECT res FROM Resposta res WHERE :solucao = true AND res.topico = :topico")
    List<Resposta> buscarRespostaPorSolucaoPorTopico(boolean solucao, Topico topico);
}
