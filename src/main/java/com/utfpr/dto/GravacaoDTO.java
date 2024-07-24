package com.utfpr.dto;

import com.utfpr.entity.Gravacao;

import java.time.LocalDate;

public record GravacaoDTO(
        long id,
        String gravadora,
        String cantor,
        String musica,
        LocalDate data
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
