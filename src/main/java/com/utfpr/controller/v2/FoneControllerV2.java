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

import com.utfpr.entity.Fone;
import com.utfpr.service.FoneService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v2/fone")
@Tag(name = "Fone V2")
public class FoneControllerV2 {

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
}
