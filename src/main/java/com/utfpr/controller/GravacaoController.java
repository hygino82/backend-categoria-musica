package com.utfpr.controller;

import com.utfpr.dto.*;
import com.utfpr.service.GravacaoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;


import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/v1/gravacao")
public class GravacaoController {

    private final GravacaoService service;

    public GravacaoController(GravacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseGravacaoDTO> inserirGravacao(@RequestBody @Valid RequestGravacaoDTO dto) {
        return ResponseEntity.status(201).body(this.service.inserirGravacao(dto));
    }

    @PostMapping("/v2")
    public ResponseEntity<ResponseGravacaoDTO> inserirGravacao(@RequestBody @Valid CadastroNovaGravacao dto) {
        return ResponseEntity.status(201).body(this.service.inserirGravacao(dto));
    }

    @GetMapping
    public ResponseEntity<Page<ResponseGravacaoDTO>> buscarGravacoes(Pageable pageable) {
        return ResponseEntity.status(200).body(this.service.buscarGravacoes(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGravacaoDTO> buscarGravacaoPorId(@PathVariable long id) {
        return ResponseEntity.status(200).body(this.service.buscarGravacaoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGravacaoDTO> atualizarGravacaoPorId(@PathVariable long id, @RequestBody @Valid RequestGravacaoDTO dto) {
        ResponseGravacaoDTO res = this.service.atualizarGravacaoPorId(id, dto);
        return ResponseEntity.status(200).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerGravacaoPorId(@PathVariable long id) {
        this.service.removerGravacaoPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}