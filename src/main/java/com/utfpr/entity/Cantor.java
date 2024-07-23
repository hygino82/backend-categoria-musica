package com.utfpr.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cantor")
@Data
@NoArgsConstructor
public class Cantor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cantor", nullable = false)
    private Long id;

    @Column(length = 50, name = "nome_cantor")
    private String nome;

    @Column(length = 30, name = "pais")
    private String pais;

    public Cantor(String nome, String pais) {
        this.nome = nome;
        this.pais = pais;
    }
}
