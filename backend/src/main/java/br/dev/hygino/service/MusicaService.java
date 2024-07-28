package br.dev.hygino.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestMusicaDTO;
import br.dev.hygino.dto.ResponseMusicaDTO;
import br.dev.hygino.entity.Categoria;
import br.dev.hygino.entity.Musica;
import br.dev.hygino.repository.CategoriaRepository;
import br.dev.hygino.repository.MusicaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

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
}
