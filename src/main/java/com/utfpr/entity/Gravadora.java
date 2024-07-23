package com.utfpr.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gravadora")
@Data
@NoArgsConstructor
public class Gravadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_gravadora", nullable = false)
    private Long id;

    @Column(length = 50, name = "nome_gravadora")
    private String nome;

    @Column(length = 50, name = "pais")
    private String pais;

    public Gravadora(String nome, String pais) {
        this.nome = nome;
        this.pais = pais;
    }
}
