package br.dev.hygino.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestGravadoraDTO;
import br.dev.hygino.dto.ResponseGravadoraDTO;
import br.dev.hygino.entity.Gravadora;
import br.dev.hygino.reporisory.GravadoraRepository;
import jakarta.validation.Valid;

@Service
public class GravadoraService {

    private final GravadoraRepository gravadoraRepository;

    public GravadoraService(GravadoraRepository gravadoraRepository) {
        this.gravadoraRepository = gravadoraRepository;
    }

    @Transactional
    public ResponseGravadoraDTO inserir(@Valid RequestGravadoraDTO dto) {
        Gravadora entity = new Gravadora(dto.nome(), dto.pais());
        entity = gravadoraRepository.save(entity);
        return new ResponseGravadoraDTO(entity);
    }
}
