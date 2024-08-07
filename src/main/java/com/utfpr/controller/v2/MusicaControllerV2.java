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

import com.utfpr.entity.Musica;
import com.utfpr.service.MusicaService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v2/musica")
@Tag(name = "Música V2")
public class MusicaControllerV2 {
    private static final Logger log = LoggerFactory.getLogger(MusicaControllerV2.class);

    @Autowired
    private MusicaService service;

    @GetMapping
    public ResponseEntity<List<Musica>> getAll() {
        final List<Musica> list = this.service.getAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> getOne(@PathVariable(value = "id") Long id) {
        Optional<Musica> musicaFound = this.service.encontrar(id);

        return musicaFound.map(musica -> new ResponseEntity<>(musica, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musica> update(@PathVariable(value = "id") Long id, @RequestBody Musica musicaUpdated) {
        log.info("PUT Musica");
        Optional<Musica> musicaOld = this.service.encontrar(id);
        if (musicaOld.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            musicaUpdated.setId(id);
            log.warn("Musica: {}", musicaUpdated);
            if (this.service.salvar(musicaUpdated) != null) {
                return new ResponseEntity<>(musicaUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping
    public ResponseEntity<Musica> create(@RequestBody Musica musica) {
        if (this.service.salvar(musica) != null) {
            return new ResponseEntity<>(musica, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Musica> remove(@PathVariable(value = "id") Long id) {
        Optional<Musica> res = this.service.encontrar(id);

        if(res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            this.service.remover(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
