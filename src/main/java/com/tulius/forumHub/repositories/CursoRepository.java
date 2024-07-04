package com.tulius.forumHub.repositories;

import com.tulius.forumHub.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<Curso, UUID> {
    Curso findByNome(String curso);

}
