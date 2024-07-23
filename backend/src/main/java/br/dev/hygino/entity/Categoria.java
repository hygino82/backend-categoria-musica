package br.dev.hygino.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_categoria", nullable = false)
    private Long id;

    @Column(length = 50, name = "desc_categoria")
    private String descCategoria;

    public Categoria() {
    }

    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", descCategoria='" + descCategoria + '\'' +
                ", musicas=" + musicas +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescCategoria() {
        return descCategoria;
    }

    public void setDescCategoria(String descCategoria) {
        this.descCategoria = descCategoria;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((descCategoria == null) ? 0 : descCategoria.hashCode());
        result = prime * result + ((musicas == null) ? 0 : musicas.hashCode());
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
        Categoria other = (Categoria) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (descCategoria == null) {
            if (other.descCategoria != null)
                return false;
        } else if (!descCategoria.equals(other.descCategoria))
            return false;
        if (musicas == null) {
            if (other.musicas != null)
                return false;
        } else if (!musicas.equals(other.musicas))
            return false;
        return true;
    }
}