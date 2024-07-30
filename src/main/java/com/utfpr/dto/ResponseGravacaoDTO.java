package com.utfpr.dto;

import com.utfpr.entity.Gravacao;

import java.time.LocalDate;



public record ResponseGravacaoDTO(
        long id,
        String cantor,
        String musica,
        String gravadora,
        String categoria,
        LocalDate dataGravacao) {
    public ResponseGravacaoDTO(Gravacao entity) {
        this(
                entity.getId(),
                entity.getCantor().getNome(),
                entity.getMusica().getTitulo(),
                entity.getGravadora().getNome(),
                entity.getMusica().getCategoria().getDescCategoria(),
                entity.getDataGravacao());
    }
}
