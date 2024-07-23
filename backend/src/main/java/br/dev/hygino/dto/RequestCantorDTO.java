package br.dev.hygino.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestCantorDTO(
                @NotBlank @Size(max = 50) String nome,
                @NotBlank @Size(max = 50) String pais) {
}
