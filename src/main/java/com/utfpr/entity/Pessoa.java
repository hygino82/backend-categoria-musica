package com.utfpr.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pessoa", nullable = false)
    private Long id;

    @Column(length = 70, name = "nome_pessoa")
    private String nome;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<Fone> telefones;
}
