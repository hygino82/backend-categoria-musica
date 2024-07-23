package br.dev.hygino.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.dto.RequestCantorDTO;
import br.dev.hygino.dto.RequestCategoriaDTO;
import br.dev.hygino.dto.ResponseCantorDTO;
import br.dev.hygino.dto.ResponseCategoriaDTO;
import br.dev.hygino.service.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/categoria")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseCategoriaDTO> inserir(@RequestBody @Valid RequestCategoriaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(dto));
    }

    @GetMapping
    ResponseEntity<List<ResponseCategoriaDTO>> buscarTodasAsCategorias() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodasAsCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCategoriaDTO> buscarPorId(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCategoriaDTO> atualizarPorId(@PathVariable long id,
            @RequestBody @Valid RequestCategoriaDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarPorId(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable long id) {
        service.removerPorId(id);
        return ResponseEntity.noContent().build();
    }
}
