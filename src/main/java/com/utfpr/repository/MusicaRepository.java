package com.utfpr.repository;

import com.utfpr.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {

    @Procedure("proc_adiciona_tempo")
    void procAdicionaTempo(Integer valor);

    @Procedure("proc_subtrai_tempo")
    void procSubtraiTempo(Integer valor);
}
