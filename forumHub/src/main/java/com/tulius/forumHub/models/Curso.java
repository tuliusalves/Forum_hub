package com.tulius.forumHub.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity(name= "Cursos")
@Table(name= "cursos")
public class Curso extends RepresentationModel<Curso> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

}
