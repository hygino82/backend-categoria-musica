package br.dev.hygino.dto;

import jakarta.validation.constraints.*;

public record RequestMusicaDTO(
        @NotBlank @Size(max = 100) String titulo,
        @NotNull Integer duracao,
        @NotNull Long categoriaId) {
}
