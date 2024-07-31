package com.utfpr.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "gravacao")
@Data
@NoArgsConstructor
public class Gravacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_gravacao", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cod_gravadora", nullable = false)
    private Gravadora gravadora;

    @OneToOne
    @JoinColumn(name = "cod_cantor", nullable = false)
    private Cantor cantor;

    @OneToOne
    @JoinColumn(name = "cod_musica", nullable = false)
    private Musica musica;

    @Column(name = "data_gravacao")
    private LocalDate dataGravacao;

    public Gravacao(Cantor cantor, Musica musica, Gravadora gravadora) {
        this.cantor = cantor;
        this.musica = musica;
        this.gravadora = gravadora;
        dataGravacao = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Gravacao{" +
                "id=" + id +
                ", gravadora=" + gravadora.getNome() +
                ", cantor=" + cantor.getNome() +
                ", musica=" + musica +
                ", dataGravacao=" + dataGravacao +
                '}';
    }

    public Gravacao(Gravadora gravadora, Cantor cantor, Musica musica, LocalDate dataGravacao) {
        this.gravadora = gravadora;
        this.cantor = cantor;
        this.musica = musica;
        this.dataGravacao = dataGravacao;
    }
}
