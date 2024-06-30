package com.tulius.forumHub.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name= "Topico")
@Table(name= "Topico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of="id", callSuper = false)
public class Topico extends RepresentationModel<Topico> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String titulo;
    @Column(unique = true, nullable = false)
    private String mensagem;
    private LocalDateTime dataCricao;
    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    
}
