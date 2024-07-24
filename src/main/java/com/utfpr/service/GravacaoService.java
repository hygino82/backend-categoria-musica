package com.utfpr.service;

import com.utfpr.dto.GravacaoDTO;
import com.utfpr.entity.*;
import com.utfpr.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GravacaoService {

    private final GravacaoRepository gravacaoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MusicaRepository musicaRepository;
    private final CantorRepository cantorRepository;
    private final GravadoraRepository gravadoraRepository;

    @Transactional
    public Gravacao novaGravacao(Categoria categoria, Musica musica, Cantor cantor, Gravadora gravadora) {
        categoria = categoriaRepository.save(categoria);
        musica.setCategoria(categoria);
        musica = musicaRepository.save(musica);
        cantor = cantorRepository.save(cantor);
        gravadora = gravadoraRepository.save(gravadora);
        Gravacao gravacao = new Gravacao(gravadora, cantor, musica, LocalDate.now());
        return gravacaoRepository.save(gravacao);
    }

    @Transactional(readOnly = true)
    public List<GravacaoDTO> buscarTodasGravacoes() {
        return gravacaoRepository.findAll()
                .stream()
                .map(GravacaoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public GravacaoDTO buscarGravacaoPorId(Long id) {
        final var gravacao = gravacaoRepository.buscarGravacaoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe gravação com o id: " + id));
        return new GravacaoDTO(gravacao);
    }
}
