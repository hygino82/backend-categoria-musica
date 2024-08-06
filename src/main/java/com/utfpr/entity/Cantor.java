package com.utfpr.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cantor")
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

    public Cantor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
