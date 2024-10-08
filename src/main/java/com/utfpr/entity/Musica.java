package com.utfpr.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "musica")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_musica", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cod_categoria", nullable = false)
    private Categoria categoria;

    private Integer duracao;

    @Column(length = 100)
    private String titulo;

    @Override
    public String toString() {
        return "Musica{" +
                "id=" + id +
                ", categoria=" + categoria +
                ", duracao=" + duracao +
                ", titulo='" + titulo + '\'' +
                '}';
    }

    public Musica(String titulo, Integer duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
    }

    public Musica() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
