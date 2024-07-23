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

import br.dev.hygino.dto.RequestGravadoraDTO;
import br.dev.hygino.dto.ResponseGravadoraDTO;
import br.dev.hygino.service.GravadoraService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/gravadora")
public class GravadoraController {

    private final GravadoraService gravadoraService;

    public GravadoraController(GravadoraService gravadoraService) {
        this.gravadoraService = gravadoraService;
    }

    @PostMapping
    public ResponseEntity<ResponseGravadoraDTO> inserir(@RequestBody @Valid RequestGravadoraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gravadoraService.inserir(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseGravadoraDTO>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(gravadoraService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGravadoraDTO> buscarPorId(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(gravadoraService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGravadoraDTO> atualizarPorId(@PathVariable long id,
            @RequestBody @Valid RequestGravadoraDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(gravadoraService.atualizarPorId(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable long id) {
        gravadoraService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
