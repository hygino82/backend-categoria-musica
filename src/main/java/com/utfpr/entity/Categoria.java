package com.utfpr.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "categoria")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_categoria", nullable = false)
    private Long id;

    @Column(name = "desc_categoria", length = 50)
    private String descCategoria;

    @OneToMany(mappedBy = "categoria",fetch = FetchType.EAGER)
    private List<Musica> musicas;

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", descCategoria='" + descCategoria + '\'' +
                ", musicas=" + musicas +
                '}';
    }
}
