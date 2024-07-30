package com.utfpr.dto;

import com.utfpr.entity.Gravadora;

public record ResponseGravadoraDTO(
        Long id,
        String nome,
        String pais) {

    public ResponseGravadoraDTO(Gravadora entity) {
        this(
                entity.getId(),
                entity.getNome(),
                entity.getPais());
    }
}
