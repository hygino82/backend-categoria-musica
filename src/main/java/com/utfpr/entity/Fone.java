package com.utfpr.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fone")
@Data
public class Fone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_fone", nullable = false)
    private Long id;

    @Column(length = 80)
    private String numero;

    private char tipo;

    @ManyToOne
    @JoinColumn(name = "cod_pessoa", nullable = false)
    private Pessoa pessoa;

    @Override
    public String toString() {
        return "Fone{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", tipo=" + tipo +
                ", pessoa=" + pessoa.getNome() +
                '}';
    }
}
