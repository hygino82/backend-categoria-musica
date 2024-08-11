package com.utfpr.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "gravadora")
public class Gravadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_gravadora", nullable = false)
    private Long id;

    @Column(length = 50, name = "nome_gravadora")
    private String nome;

    @Column(length = 50, name = "pais")
    private String pais;

    public Gravadora() {
    }

    public Gravadora(String nome, String pais) {
        this.nome = nome;
        this.pais = pais;
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
