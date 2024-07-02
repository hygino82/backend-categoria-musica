package com.utfpr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utfpr.entity.Musica;
import com.utfpr.repository.MusicaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MusicaService {

    private final MusicaRepository musicaRepository;

    @Transactional(readOnly = true)
    public List<Musica> listarTodasMusicas() {
        return musicaRepository.findAll();
    }
}
