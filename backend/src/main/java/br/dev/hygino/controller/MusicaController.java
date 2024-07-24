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

import br.dev.hygino.dto.RequestMusicaDTO;
import br.dev.hygino.dto.ResponseMusicaDTO;
import br.dev.hygino.service.MusicaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/musica")
public class MusicaController {

    private final MusicaService service;

    public MusicaController(MusicaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseMusicaDTO> inserir(@RequestBody @Valid RequestMusicaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserirMusica(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseMusicaDTO>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodasMusicas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMusicaDTO> buscarPorId(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMusicaDTO> atualizarPorId(@PathVariable long id,
            @RequestBody @Valid RequestMusicaDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarPorId(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
