package br.dev.hygino.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.dev.hygino.dto.RequestCantorDTO;
import br.dev.hygino.dto.ResponseCantorDTO;
import br.dev.hygino.service.CantorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/cantor")
public class CantorController {

    private final CantorService service;

    public CantorController(CantorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseCantorDTO> inserir(@RequestBody @Valid RequestCantorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseCantorDTO>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCantorDTO> buscarPorId(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCantorDTO> atualizarPorId(@PathVariable long id,
            @RequestBody @Valid RequestCantorDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarPorId(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
