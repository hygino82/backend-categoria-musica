package br.dev.hygino.dto;

import br.dev.hygino.entity.Cantor;

public record ResponseCantorDTO(
        Long id,
        String nome,
        String pais) {

    public ResponseCantorDTO(Cantor entity) {
        this(
                entity.getId(),
                entity.getNome(),
                entity.getPais());
    }
}
