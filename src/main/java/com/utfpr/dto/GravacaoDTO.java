package com.utfpr.dto;

import com.utfpr.entity.Gravacao;

import java.time.LocalDateTime;

public record GravacaoDTO(
        Long id,
        String gravadora,
        String cantor,
        String musica,
        LocalDateTime data
) {
    public GravacaoDTO(Gravacao entity) {
        this(
                entity.getId(),
                entity.getGravadora().getNome(),
                entity.getCantor().getNome(),
                entity.getMusica().getTitulo(),
                entity.getDataGravacao()
        );
    }
}
