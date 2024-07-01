package com.tulius.forumHub.repositories;

import com.tulius.forumHub.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopicoRepository extends JpaRepository<Topico, UUID> {
}
