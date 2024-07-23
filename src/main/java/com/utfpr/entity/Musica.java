package com.utfpr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "musica")
@Data
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
                ", categoria=" + categoria.getDescCategoria() +
                ", duracao=" + duracao +
                ", titulo='" + titulo + '\'' +
                '}';
    }

    public Musica(String titulo, Integer duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
    }
}
