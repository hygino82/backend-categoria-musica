package com.utfpr.controller.v2;

import com.utfpr.entity.Cantor;
import com.utfpr.entity.Fone;
import com.utfpr.entity.Gravacao;
import com.utfpr.service.GravacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v2/gravacao")
@Tag(name = "Gravação V2")
public class GravacaoControllerV2 {
    private static final Logger log = LoggerFactory.getLogger(GravacaoControllerV2.class);

    private GravacaoService service;

    public GravacaoControllerV2(GravacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Gravacao>> getAll() {
        final List<Gravacao> list = this.service.getAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gravacao> getOne(@PathVariable(value = "id") Long id) {
        Optional<Gravacao> GravacaoFound = this.service.encontrar(id);

        return GravacaoFound.map(Gravacao -> new ResponseEntity<>(Gravacao, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gravacao> update(@PathVariable(value = "id") Long id, @RequestBody Gravacao gravacaoUpdated) {
        Optional<Gravacao> gravacaoOld = this.service.encontrar(id);
        log.warn("Teste put endpoint");
        log.warn("Gravacao: {}", gravacaoUpdated);
        if (gravacaoOld.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            gravacaoUpdated.setId(id);
            log.warn("Gravacao: {}", gravacaoUpdated);
            if (this.service.salvar(gravacaoUpdated) != null) {
                return new ResponseEntity<>(gravacaoUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping
    public ResponseEntity<Gravacao> create(@RequestBody Gravacao gravacao) {
        if (this.service.salvar(gravacao) != null) {
            return new ResponseEntity<>(gravacao, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Gravacao> remove(@PathVariable(value = "id") Long id) {
        Optional<Gravacao> res = this.service.encontrar(id);

        if(res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            this.service.removerGravacaoPorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
