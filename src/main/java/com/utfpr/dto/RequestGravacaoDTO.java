package com.utfpr.dto;

import jakarta.validation.constraints.NotNull;

public record RequestGravacaoDTO(
        @NotNull Long cantorId,
        @NotNull Long musicaId,
        @NotNull Long gravadoraId) {

}
