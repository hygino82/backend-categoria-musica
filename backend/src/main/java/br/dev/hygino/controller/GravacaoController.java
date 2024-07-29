package br.dev.hygino.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.dto.CadastroNovaGravacao;
import br.dev.hygino.dto.RequestGravacaoDTO;
import br.dev.hygino.dto.ResponseGravacaoDTO;
import br.dev.hygino.service.GravacaoService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public ResponseEntity<ResponseGravacaoDTO> buscarGracacaoPorId(@PathVariable long id) {
        return ResponseEntity.status(200).body(this.service.buscarGravacaoPorId(id));
    }
}
