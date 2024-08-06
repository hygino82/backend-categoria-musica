package com.utfpr.controller.v2;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utfpr.BackendAcervoMusicalApiApplication;
import com.utfpr.entity.Categoria;
import com.utfpr.entity.Categoria;
import com.utfpr.service.CategoriaService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v2/categoria")
@Tag(name = "Categoria V2")
public class CategoriaControllerV2 {
    private static final Logger log = LoggerFactory.getLogger(CategoriaControllerV2.class);

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        final List<Categoria> list = this.service.getAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getOne(@PathVariable(value = "id") Long id) {
        Optional<Categoria> categoryFound = this.service.encontrar(id);

        return categoryFound.map(categoria -> new ResponseEntity<>(categoria, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable(value = "id") Long id,
            @RequestBody Categoria categoriaUpdated) {
        Optional<Categoria> categoriaOld = this.service.encontrar(id);
        if (categoriaOld.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            categoriaUpdated.setId(id);
            log.warn("Categoria: {}", categoriaUpdated);
            if (this.service.salvar(categoriaUpdated) != null) {
                return new ResponseEntity<>(categoriaUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
