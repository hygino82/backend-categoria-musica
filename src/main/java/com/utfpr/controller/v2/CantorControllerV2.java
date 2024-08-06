package com.utfpr.controller.v2;

import com.utfpr.entity.Cantor;
import com.utfpr.service.CantorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v2/cantor")
@Tag(name = "Cantor V2")
public class CantorControllerV2 {

    private static final Logger log = LoggerFactory.getLogger(CantorControllerV2.class);

    @Autowired
    private CantorService service;

    @GetMapping
    public ResponseEntity<List<Cantor>> getAll() {
        final List<Cantor> list = this.service.getAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cantor> getOne(@PathVariable(value = "id") Long id) {
        Optional<Cantor> cantorFound = this.service.encontrar(id);

        return cantorFound.map(cantor -> new ResponseEntity<>(cantor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cantor> update(@PathVariable(value = "id") Long id, @RequestBody Cantor cantorUpdated) {
        Optional<Cantor> cantorOld = this.service.encontrar(id);
        if (cantorOld.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            cantorUpdated.setId(id);
            log.warn("Cantor: {}", cantorUpdated);
            if (this.service.salvar(cantorUpdated) != null) {
                return new ResponseEntity<>(cantorUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping
    public ResponseEntity<Cantor> create(@RequestBody Cantor cantor) {
        if (this.service.salvar(cantor) != null) {
            return new ResponseEntity<>(cantor, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
