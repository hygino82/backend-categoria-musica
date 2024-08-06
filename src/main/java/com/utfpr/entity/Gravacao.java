package com.utfpr.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "gravacao")
public class Gravacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_gravacao", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cod_gravadora", nullable = false)
    private Gravadora gravadora;

    @ManyToOne
    @JoinColumn(name = "cod_cantor", nullable = false)
    private Cantor cantor;

    @ManyToOne
    @JoinColumn(name = "cod_musica", nullable = false)
    private Musica musica;

    @Column(name = "data_gravacao")
    @DateTimeFormat
    private LocalDateTime dataGravacao;

    public Gravacao(Cantor cantor, Musica musica, Gravadora gravadora) {
        this.cantor = cantor;
        this.musica = musica;
        this.gravadora = gravadora;
        dataGravacao = LocalDateTime.now();
    }

    public Gravacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gravadora getGravadora() {
        return gravadora;
    }

    public void setGravadora(Gravadora gravadora) {
        this.gravadora = gravadora;
    }

    public Cantor getCantor() {
        return cantor;
    }

    public void setCantor(Cantor cantor) {
        this.cantor = cantor;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public LocalDateTime getDataGravacao() {
        return dataGravacao;
    }

    public void setDataGravacao(LocalDateTime dataGravacao) {
        this.dataGravacao = dataGravacao;
    }

    @Override
    public String toString() {
        return "Gravacao{" +
                "id=" + id +
                ", gravadora=" + gravadora.getNome() +
                ", cantor=" + cantor.getNome() +
                ", musica=" + musica.getTitulo() +
                ", dataGravacao=" + dataGravacao +
                '}';
    }
}
