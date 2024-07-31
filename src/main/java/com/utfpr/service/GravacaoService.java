package com.utfpr.service;

import java.time.LocalDate;

import com.utfpr.dto.*;
import com.utfpr.entity.*;
import com.utfpr.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;

@Service
public class GravacaoService {

    private final GravacaoRepository gravacaoRepository;
    private final GravadoraRepository gravadoraRepository;
    private final CantorRepository cantorRepository;
    private final MusicaRepository musicaRepository;
    private final CategoriaRepository categoriaRepository;

    public GravacaoService(GravacaoRepository gravacaoRepository, GravadoraRepository gravadoraRepository,
            CantorRepository cantorRepository, MusicaRepository musicaRepository,
            CategoriaRepository categoriaRepository) {
        this.gravacaoRepository = gravacaoRepository;
        this.gravadoraRepository = gravadoraRepository;
        this.cantorRepository = cantorRepository;
        this.musicaRepository = musicaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public ResponseGravacaoDTO inserirGravacao(@Valid RequestGravacaoDTO dto) {
        var musica = this.musicaRepository.findById(dto.musicaId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Não existe música com o id: " + dto.musicaId()));
        var cantor = this.cantorRepository.findById(dto.cantorId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Não existe cantor com o id: " + dto.cantorId()));
        var gravadora = this.gravadoraRepository.findById(dto.gravadoraId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Não existe gravadora com o id: " + dto.gravadoraId()));

        var gravacao = new Gravacao();
        copyProperties(musica, cantor, gravadora, gravacao);

        return new ResponseGravacaoDTO(this.gravacaoRepository.save(gravacao));
    }

    private void copyProperties(Musica musica, Cantor cantor, Gravadora gravadora, Gravacao gravacao) {
        gravacao.setDataGravacao(LocalDate.now());
        gravacao.setCantor(cantor);
        gravacao.setGravadora(gravadora);
        gravacao.setMusica(musica);
    }

    @Transactional(readOnly = true)
    public Page<ResponseGravacaoDTO> buscarGravacoes(Pageable pageable) {
        return this.gravacaoRepository.findAll(pageable).map(ResponseGravacaoDTO::new);
    }

    @Transactional(readOnly = true)
    public ResponseGravacaoDTO buscarGravacaoPorId(long id) {
        final var response = this.gravacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe gravação com o id: " + id));
        return new ResponseGravacaoDTO(response);
    }

    @Transactional
    public ResponseGravacaoDTO inserirGravacao(@Valid CadastroNovaGravacao dto) {
        try {

            var categoria = this.categoriaRepository.findById(dto.musica().categoriaId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Não existe categoria com o id: "
                                    + dto.musica().categoriaId()));
            var musica = new Musica(dto.musica().titulo(), dto.musica().duracao());
            musica.setCategoria(categoria);
            musica = this.musicaRepository.save(musica);
            var cantor = new Cantor(dto.cantor().nome(), dto.cantor().pais());
            cantor = this.cantorRepository.save(cantor);
            var gravadora = new Gravadora(dto.gravadora().nome(), dto.gravadora().pais());
            gravadora = gravadoraRepository.save(gravadora);
            var gravacao = new Gravacao(cantor, musica, gravadora);
            gravacao.setDataGravacao(dto.dataGravacao());
            return new ResponseGravacaoDTO(this.gravacaoRepository.save(gravacao));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Erro ao salvar a gravação");
        }

    }

    @Transactional
    public ResponseGravacaoDTO atualizarGravacaoPorId(long id, RequestGravacaoDTO dto) {
        final var musica = this.musicaRepository.findById(dto.musicaId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Não existe música com o id: " + dto.musicaId()));
        final var cantor = this.cantorRepository.findById(dto.cantorId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Não existe cantor com o id: " + dto.cantorId()));
        final var gravadora = this.gravadoraRepository.findById(dto.gravadoraId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Não existe gravadora com o id: " + dto.gravadoraId()));
        try {
            var gravacao = this.gravacaoRepository.getReferenceById(id);
            copyProperties(musica, cantor, gravadora, gravacao);
            gravacao = this.gravacaoRepository.save(gravacao);
            return new ResponseGravacaoDTO(gravacao);
        } catch (EntityNotFoundException ex) {
            throw new IllegalArgumentException("Não existe Gravação com o id: " + id);
        }

    }

    public void removerGravacaoPorId(long id) {
        this.gravacaoRepository.deleteById(id);
    }
}