package com.utfpr.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fone")
public class Fone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_fone", nullable = false)
    private Long id;

    @Column(length = 80)
    private String numero;

    @Column(length = 1)
    private char tipo;

    @ManyToOne
    @JoinColumn(name = "cod_pessoa", nullable = false)
    private Pessoa pessoa;

    public Fone() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

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
