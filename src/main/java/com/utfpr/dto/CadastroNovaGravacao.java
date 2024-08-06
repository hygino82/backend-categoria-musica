package com.utfpr.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CadastroNovaGravacao(
        @NotNull RequestCantorDTO cantor,
        @NotNull RequestMusicaDTO musica,
        @NotNull RequestGravadoraDTO gravadora,
        LocalDateTime dataGravacao) {
}
