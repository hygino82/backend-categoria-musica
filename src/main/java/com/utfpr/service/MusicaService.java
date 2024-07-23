package com.utfpr.service;

import java.util.List;

import com.utfpr.entity.Musica;
import org.springframework.stereotype.Service;

import com.utfpr.repository.MusicaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MusicaService {

    private final MusicaRepository musicaRepository;

    //@Transactional(readOnly = true)
    public List<Musica> listarTodasMusicas() {
        return musicaRepository.findAll();
    }

    public void adicionarTempo(Integer valor) {
        musicaRepository.procAdicionaTempo(valor);
    }

    public void subtrairTempo(Integer valor) {
        musicaRepository.procSubtraiTempo(valor);
    }
}
