package br.dev.hygino.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
