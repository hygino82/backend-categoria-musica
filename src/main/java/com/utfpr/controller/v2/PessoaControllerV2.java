package com.utfpr.controller.v2;

import com.utfpr.BackendAcervoMusicalApiApplication;
import com.utfpr.entity.Pessoa;
import com.utfpr.service.PessoaService;
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
@RequestMapping("api/v2/pessoa")
@Tag(name = "Pessoa V2")
public class PessoaControllerV2 {
    private static final Logger log = LoggerFactory.getLogger(BackendAcervoMusicalApiApplication.class);

    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll() {
        final List<Pessoa> list = this.service.listarTodasPessoas();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getOne(@PathVariable(value = "id") Long id) {
        Optional<Pessoa> pessoaFound = this.service.encontrar(id);

        return pessoaFound.map(pessoa -> new ResponseEntity<>(pessoa, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable(value = "id") Long id, @RequestBody Pessoa pessoaUpdated) {
        log.warn("Executando PUT Pessoa");
        Optional<Pessoa> PessoaOld = this.service.encontrar(id);
        if (PessoaOld.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            pessoaUpdated.setId(id);
            log.warn("Pessoa: {}", pessoaUpdated);
            if (this.service.salvar(pessoaUpdated) != null) {
                return new ResponseEntity<>(pessoaUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa) {
        log.info("Inserindo pessoa");
        if (this.service.salvar(pessoa) != null) {
            return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> remove(@PathVariable(value = "id") Long id) {
        Optional<Pessoa> res = this.service.encontrar(id);

        if(res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            this.service.removerPessoa(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
