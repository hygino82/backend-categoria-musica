package br.dev.hygino.dto;

import java.util.List;

import br.dev.hygino.entity.Categoria;

public record ResponseCategoriaDTO(Long id, String descCategoria, List<ResponseMusicaDTO> musicas) {
    public ResponseCategoriaDTO(Categoria entity) {
        this(
                entity.getId(),
                entity.getDescCategoria(),
                entity.getMusicas().stream()
                        .map(ResponseMusicaDTO::new)
                        .toList());
    }
}
