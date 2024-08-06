package com.utfpr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utfpr.dto.RequestCantorDTO;
import com.utfpr.dto.ResponseCantorDTO;
import com.utfpr.entity.Cantor;
import com.utfpr.repository.CantorRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class CantorService {

    private final CantorRepository cantorRepository;

    public CantorService(CantorRepository cantorRepository) {
        this.cantorRepository = cantorRepository;
    }

    @Transactional
    public ResponseCantorDTO inserir(@Valid RequestCantorDTO dto) {
        Cantor entity = new Cantor();
        dtoToEntity(dto, entity);
        entity = cantorRepository.save(entity);
        return new ResponseCantorDTO(entity);
    }

    private void dtoToEntity(@Valid RequestCantorDTO dto, Cantor entity) {
        entity.setNome(dto.nome());
        entity.setPais(dto.pais());
    }

    @Transactional(readOnly = true)
    public List<ResponseCantorDTO> buscarTodos() {
        return cantorRepository.findAll()
                .stream()
                .map(ResponseCantorDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ResponseCantorDTO buscarPorId(Long id) {
        final var entity = cantorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe Cantor com o id: " + id));
        return new ResponseCantorDTO(entity);
    }

    @Transactional
    public ResponseCantorDTO atualizarPorId(Long id, @Valid RequestCantorDTO dto) {
        try {
            Cantor entity = cantorRepository.getReferenceById(id);
            dtoToEntity(dto, entity);
            entity = cantorRepository.save(entity);
            return new ResponseCantorDTO(entity);
        } catch (EntityNotFoundException ex) {
            throw new IllegalArgumentException("Não existe Cantor com o id: " + id);
        }
    }

    public void remover(Long id) {
        try {
            cantorRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Não pode remover cantor associado a uma gravação");
        }
    }

    @Transactional(readOnly = true)
    public List<Cantor> getAll() {
        return this.cantorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Cantor> encontrar(Long id) {
        return this.cantorRepository.findById(id);
    }

    public Cantor salvar(Cantor cantor) {
        try {
            return this.cantorRepository.saveAndFlush(cantor);
        } catch (Exception ex) {
            return null;
        }
    }
}