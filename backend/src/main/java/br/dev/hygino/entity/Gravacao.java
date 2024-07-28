package br.dev.hygino.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gravacao")
public class Gravacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_categoria", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cantor_id")
    private Cantor cantor;

    @ManyToOne
    @JoinColumn(name = "gravadora_id")
    private Gravadora gravadora;

    @ManyToOne
    @JoinColumn(name = "musica_id")
    private Musica musica;
    
    private LocalDate dataGravacao;

    public Gravacao() {
    }

    public Gravacao(Cantor cantor, Gravadora gravadora, Musica musica) {
        this.cantor = cantor;
        this.gravadora = gravadora;
        this.musica = musica;
        dataGravacao = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cantor getCantor() {
        return cantor;
    }

    public void setCantor(Cantor cantor) {
        this.cantor = cantor;
    }

    public Gravadora getGravadora() {
        return gravadora;
    }

    public void setGravadora(Gravadora gravadora) {
        this.gravadora = gravadora;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public LocalDate getDataGravacao() {
        return dataGravacao;
    }

    public void setDataGravacao(LocalDate dataGravacao) {
        this.dataGravacao = dataGravacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((cantor == null) ? 0 : cantor.hashCode());
        result = prime * result + ((gravadora == null) ? 0 : gravadora.hashCode());
        result = prime * result + ((musica == null) ? 0 : musica.hashCode());
        result = prime * result + ((dataGravacao == null) ? 0 : dataGravacao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Gravacao other = (Gravacao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (cantor == null) {
            if (other.cantor != null)
                return false;
        } else if (!cantor.equals(other.cantor))
            return false;
        if (gravadora == null) {
            if (other.gravadora != null)
                return false;
        } else if (!gravadora.equals(other.gravadora))
            return false;
        if (musica == null) {
            if (other.musica != null)
                return false;
        } else if (!musica.equals(other.musica))
            return false;
        if (dataGravacao == null) {
            if (other.dataGravacao != null)
                return false;
        } else if (!dataGravacao.equals(other.dataGravacao))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Gravacao [id=" + id + ", cantor=" + cantor.getNome() + ", gravadora=" + gravadora.getNome()
                + ", musica=" + musica.getTitulo()
                + ", dataGravacao=" + dataGravacao + "]";
    }
}
