package com.utfpr.dto;

import com.utfpr.entity.Musica;

public record ResponseMusicaDTO(Long id, String titulo, Integer duracao, String categoria) {
    public ResponseMusicaDTO(Musica entity) {
        this(
                entity.getId(),
                entity.getTitulo(),
                entity.getDuracao(),
                entity.getCategoria().getDescCategoria());
    }
}