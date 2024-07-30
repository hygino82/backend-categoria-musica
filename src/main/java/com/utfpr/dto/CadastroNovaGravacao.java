package com.utfpr.dto;

import jakarta.validation.constraints.NotNull;

public record CadastroNovaGravacao(
        @NotNull RequestCantorDTO cantor,
        @NotNull RequestMusicaDTO musica,
        @NotNull RequestGravadoraDTO gravadora) {
}
