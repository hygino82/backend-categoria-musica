package com.utfpr.controller.v2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utfpr.entity.Gravadora;
import com.utfpr.service.GravadoraService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v2/Gravadora")
@Tag(name = "Gravadora V2")
public class GravadoraControllerV2 {

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
}
