package com.lucas.Cadastro_usuario.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuario")
@Entity


public class Usuario {

    @Id
    // AUTO: Deixa o banco/Hibernate decidir a melhor forma (pode ser sequence ou identity).
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // unique = true: Cria uma restrição (constraint) no banco para impedir emails duplicados.
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "nome")
    private String nome;
}
