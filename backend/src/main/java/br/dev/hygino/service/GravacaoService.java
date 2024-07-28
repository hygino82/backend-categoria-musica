package br.dev.hygino.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestGravacaoDTO;
import br.dev.hygino.dto.ResponseGravacaoDTO;
import br.dev.hygino.entity.Cantor;
import br.dev.hygino.entity.Gravacao;
import br.dev.hygino.entity.Gravadora;
import br.dev.hygino.entity.Musica;
import br.dev.hygino.repository.CantorRepository;
import br.dev.hygino.repository.GravacaoRepository;
import br.dev.hygino.repository.GravadoraRepository;
import br.dev.hygino.repository.MusicaRepository;
import jakarta.validation.Valid;

@Service
public class GravacaoService {

        private final GravacaoRepository gravacaoRepository;
        private final GravadoraRepository gravadoraRepository;
        private final CantorRepository cantorRepository;
        private final MusicaRepository musicaRepository;

        public GravacaoService(
                        GravacaoRepository gravacaoRepository,
                        GravadoraRepository gravadoraRepository,
                        CantorRepository cantorRepository,
                        MusicaRepository musicaRepository) {
                this.gravacaoRepository = gravacaoRepository;
                this.gravadoraRepository = gravadoraRepository;
                this.cantorRepository = cantorRepository;
                this.musicaRepository = musicaRepository;
        }

        @Transactional
        public ResponseGravacaoDTO inserirGravacao(@Valid RequestGravacaoDTO dto) {
                final var musica = this.musicaRepository.findById(dto.musicaId())
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "Não existe música com o id: " + dto.musicaId()));
                final var cantor = this.cantorRepository.findById(dto.cantorId())
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "Não existe cantor com o id: " + dto.cantorId()));
                final var gravadora = this.gravadoraRepository.findById(dto.gravadoraId())
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

}
