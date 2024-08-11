package com.utfpr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pessoa", nullable = false)
    private Long id;

    @Column(length = 70, name = "nome_pessoa")
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<Fone> telefones = new ArrayList<>();

    public Pessoa() {
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

    public List<Fone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Fone> telefones) {
        this.telefones = telefones;
    }
}
