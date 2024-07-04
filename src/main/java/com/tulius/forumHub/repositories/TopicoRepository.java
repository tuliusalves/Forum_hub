package com.tulius.forumHub.repositories;

import com.tulius.forumHub.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, UUID> {
}
