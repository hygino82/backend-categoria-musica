package com.utfpr.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fone")
@Data
@NoArgsConstructor
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

    public Fone(String numero, char tipo, Pessoa pessoa) {
        this.numero = numero;
        this.tipo = tipo;
        this.pessoa = pessoa;
    }
}
