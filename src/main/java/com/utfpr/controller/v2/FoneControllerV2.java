package com.utfpr.controller.v2;

import java.util.List;
import java.util.Optional;

import com.utfpr.entity.Categoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utfpr.entity.Fone;
import com.utfpr.service.FoneService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v2/fone")
@Tag(name = "Fone V2")
public class FoneControllerV2 {
    private static final Logger log = LoggerFactory.getLogger(FoneControllerV2.class);

    @Autowired
    private FoneService service;

    @GetMapping
    public ResponseEntity<List<Fone>> getAll() {
        final List<Fone> list = this.service.listarTodosTelefones();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fone> getOne(@PathVariable(value = "id") Long id) {
        Optional<Fone> categoryFound = this.service.encontrar(id);

        return categoryFound.map(Fone -> new ResponseEntity<>(Fone, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fone> update(@PathVariable(value = "id") Long id,
            @RequestBody Fone foneUpdated) {
        Optional<Fone> foneOld = this.service.encontrar(id);
        if (foneOld.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            foneUpdated.setId(id);
            log.warn("Fone: {}", foneUpdated);
            if (this.service.salvar(foneUpdated) != null) {
                return new ResponseEntity<>(foneUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping
    public ResponseEntity<Fone> create(@RequestBody Fone fone) {
        if (this.service.salvar(fone) != null) {
            return new ResponseEntity<>(fone, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
