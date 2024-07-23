package br.dev.hygino.dto;

import br.dev.hygino.entity.Musica;

public record ResponseMusicaDTO(Long id, String titulo, Integer duracao, String categoria) {
    public ResponseMusicaDTO(Musica entity) {
        this(
                entity.getId(),
                entity.getTitulo(),
                entity.getDuracao(),
                entity.getCategoria().getDescCategoria());
    }
}
