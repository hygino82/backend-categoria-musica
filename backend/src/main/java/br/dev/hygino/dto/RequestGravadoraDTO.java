package br.dev.hygino.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestGravadoraDTO(
        @NotBlank @Size(max = 50) String nome,
        @NotBlank @Size(max = 50) String pais) {
}
