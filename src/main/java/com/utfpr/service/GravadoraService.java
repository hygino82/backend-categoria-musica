package com.utfpr.service;

import java.util.List;

import com.utfpr.dto.*;
import com.utfpr.entity.Gravadora;
import com.utfpr.repository.GravadoraRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class GravadoraService {

    private final GravadoraRepository gravadoraRepository;

    public GravadoraService(GravadoraRepository gravadoraRepository) {
        this.gravadoraRepository = gravadoraRepository;
    }

    @Transactional
    public ResponseGravadoraDTO inserir(@Valid RequestGravadoraDTO dto) {
        Gravadora entity = new Gravadora();
        dtoToEntity(dto, entity);
        entity = gravadoraRepository.save(entity);
        return new ResponseGravadoraDTO(entity);
    }

    private void dtoToEntity(@Valid RequestGravadoraDTO dto, Gravadora entity) {
        entity.setNome(dto.nome());
        entity.setPais(dto.pais());
    }

    @Transactional(readOnly = true)
    public List<ResponseGravadoraDTO> buscarTodos() {
        return gravadoraRepository.findAll()
                .stream()
                .map(ResponseGravadoraDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ResponseGravadoraDTO buscarPorId(Long id) {
        final var entity = gravadoraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe Gravadora com o id: " + id));
        return new ResponseGravadoraDTO(entity);
    }

    @Transactional
    public ResponseGravadoraDTO atualizarPorId(Long id, @Valid RequestGravadoraDTO dto) {
        try {
            Gravadora entity = gravadoraRepository.getReferenceById(id);
            dtoToEntity(dto, entity);
            entity = gravadoraRepository.save(entity);
            return new ResponseGravadoraDTO(entity);
        } catch (EntityNotFoundException ex) {
            throw new IllegalArgumentException("Não existe Gravadora com o id: " + id);
        }
    }

    public void remover(Long id) {
        try {
            gravadoraRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Não pode remover música associada a uma gravação");
        }
    }
}