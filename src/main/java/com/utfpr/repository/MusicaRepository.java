package com.utfpr.repository;

import com.utfpr.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

   /* @Procedure("proc_adiciona_tempo")
    void procAdicionaTempo(Integer valor);*/
}
