package br.dev.hygino.dto;

import java.time.LocalDate;

import br.dev.hygino.entity.Gravacao;

public record ResponseGravacaoDTO(
        long id,
        String cantor,
        String musica,
        String gravadora,
        LocalDate dataGravacao) {
    public ResponseGravacaoDTO(Gravacao entity) {
        this(
                entity.getId(),
                entity.getCantor().getNome(),
                entity.getMusica().getTitulo(),
                entity.getGravadora().getNome(),
                entity.getDataGravacao());
    }
}
