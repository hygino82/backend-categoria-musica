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

import com.utfpr.entity.Cantor;
import com.utfpr.service.CantorService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v2/cantor")
@Tag(name = "Cantor V2")
public class CantorControllerV2 {

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
}
