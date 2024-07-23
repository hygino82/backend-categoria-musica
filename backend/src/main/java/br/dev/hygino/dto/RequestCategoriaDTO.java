package br.dev.hygino.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestCategoriaDTO(@NotBlank @Size(max = 50) String descCategoria) {

}
