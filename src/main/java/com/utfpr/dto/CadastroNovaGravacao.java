package com.utfpr.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record CadastroNovaGravacao(
        @NotNull RequestCantorDTO cantor,
        @NotNull RequestMusicaDTO musica,
        @NotNull RequestGravadoraDTO gravadora,
        @NotNull LocalDate dataGravacao) {
}
