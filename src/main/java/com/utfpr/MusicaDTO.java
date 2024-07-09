package com.utfpr;

import com.utfpr.entity.Musica;

public record MusicaDTO(Long id, String titulo, Integer duracao, String categoria) {
    public MusicaDTO(Musica entity) {
        this(entity.getId(), entity.getTitulo(), entity.getDuracao(), entity.getCategoria().getDescCategoria());
    }
}
