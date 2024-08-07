package com.utfpr.controller.v2;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.utfpr.entity.Gravadora;
import com.utfpr.service.GravadoraService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v2/gravadora")
@Tag(name = "Gravadora V2")
public class GravadoraControllerV2 {
    private static final Logger log = LoggerFactory.getLogger(GravadoraControllerV2.class);

    @Autowired
    private GravadoraService service;

    @GetMapping
    public ResponseEntity<List<Gravadora>> getAll() {
        final List<Gravadora> list = this.service.getAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gravadora> getOne(@PathVariable(value = "id") Long id) {
        Optional<Gravadora> gravadoraFound = this.service.encontrar(id);

        return gravadoraFound.map(gravadora -> new ResponseEntity<>(gravadora, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gravadora> update(@PathVariable(value = "id") Long id,
            @RequestBody Gravadora gravadoraUpdated) {
        Optional<Gravadora> gravadoraOld = this.service.encontrar(id);
        if (gravadoraOld.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            gravadoraUpdated.setId(id);
            log.warn("Gravadora: {}", gravadoraUpdated);
            if (this.service.salvar(gravadoraUpdated) != null) {
                return new ResponseEntity<>(gravadoraUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping
    public ResponseEntity<Gravadora> create(@RequestBody Gravadora gravadora) {
        if (this.service.salvar(gravadora) != null) {
            return new ResponseEntity<>(gravadora, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Gravadora> remove(@PathVariable(value = "id") Long id) {
        Optional<Gravadora> res = this.service.encontrar(id);

        if(res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            this.service.remover(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
