package com.utfpr.service;

import com.utfpr.dto.*;
import com.utfpr.entity.*;
import com.utfpr.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MusicaService {
    private final MusicaRepository musicaRepository;
    private final CategoriaRepository categoriaRepository;

    public MusicaService(MusicaRepository musicaRepository, CategoriaRepository categoriaRepository) {
        this.musicaRepository = musicaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public ResponseMusicaDTO inserirMusica(@Valid RequestMusicaDTO dto) {
        Musica musicaEntity = new Musica();
        Categoria categoriaEntity = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Não existe categoria com o id: " + dto.categoriaId()));
        musicaEntity.setCategoria(categoriaEntity);
        dtoToEntity(dto, musicaEntity);
        musicaEntity = musicaRepository.save(musicaEntity);
        return new ResponseMusicaDTO(musicaEntity);
    }

    private void dtoToEntity(@Valid RequestMusicaDTO dto, Musica entity) {
        entity.setDuracao(dto.duracao());
        entity.setTitulo(dto.titulo());
    }

    @Transactional(readOnly = true)
    public List<ResponseMusicaDTO> buscarTodasMusicas() {
        return musicaRepository.findAll().stream().map(ResponseMusicaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ResponseMusicaDTO buscarPorId(Long id) {
        Musica entity = musicaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe música com o id: " + id));
        return new ResponseMusicaDTO(entity);
    }

    public void remover(Long id) {
        try {
            musicaRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Não pode remover musica associada a uma gravação");
        }
    }

    @Transactional
    public ResponseMusicaDTO atualizarPorId(long id, @Valid RequestMusicaDTO dto) {
        try {
            Musica musicaEntity = musicaRepository.getReferenceById(id);
            Categoria categoriaEntity = categoriaRepository.findById(dto.categoriaId())
                    .orElseThrow(
                            () -> new IllegalArgumentException("Não existe categoria com o id: " + dto.categoriaId()));
            musicaEntity.setCategoria(categoriaEntity);
            dtoToEntity(dto, musicaEntity);
            musicaEntity = musicaRepository.save(musicaEntity);
            return new ResponseMusicaDTO(musicaEntity);
        } catch (EntityNotFoundException ex) {
            throw new IllegalArgumentException("Não existe musica com o id: " + id);
        }
    }

    @Transactional(readOnly = true)
    public Optional<Musica> encontrar(Long id) {
        return this.musicaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Musica> getAll() {
        return this.musicaRepository.findAll();
    }

    public Musica salvar(Musica musica) {
        try {
            return this.musicaRepository.save(musica);
        } catch (Exception ex) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public ResponseMusicaDTO buscarPorTitulo(String titulo) {
        Musica entity = musicaRepository.buscarPorTitulo(titulo)
                .orElseThrow(() -> new IllegalArgumentException("Não existe música com o titulo: " + titulo));
        return new ResponseMusicaDTO(entity);
    }

    @Transactional(readOnly = true)
    public Optional<Musica> findFirstByDuracaoDesc() {
        return this.musicaRepository.findFirstByOrderByDuracaoDesc();
    }

    @Transactional(readOnly = true)
    public List<Musica> findByDuracaoGreaterThan(Integer duracao) {
        return this.musicaRepository.buscarMusicasComDuracaoMaiorQue(duracao);
    }
}