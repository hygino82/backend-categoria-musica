package br.dev.hygino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.hygino.entity.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {

}
