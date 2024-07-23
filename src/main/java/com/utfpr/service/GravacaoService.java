package com.utfpr.service;

import com.utfpr.entity.*;
import com.utfpr.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class GravacaoService {

    private final GravacaoRepository gravacaoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MusicaRepository musicaRepository;
    private final CantorRepository cantorRepository;
    private final GravadoraRepository gravadoraRepository;

    public Gravacao novaGravacao(Categoria categoria, Musica musica, Cantor cantor, Gravadora gravadora) {
        categoria = categoriaRepository.save(categoria);
        musica.setCategoria(categoria);
        musica = musicaRepository.save(musica);
        cantor = cantorRepository.save(cantor);
        gravadora = gravadoraRepository.save(gravadora);
        Gravacao gravacao = new Gravacao(gravadora, cantor, musica, LocalDate.now());
        return gravacaoRepository.save(gravacao);
    }
}
