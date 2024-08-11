package com.utfpr.repository;

import com.utfpr.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {

    @Procedure("proc_adiciona_tempo")
    void procAdicionaTempo(Integer valor);

    @Procedure("proc_subtrai_tempo")
    void procSubtraiTempo(Integer valor);

    //usando JPQL
    @Query("""
            select obj from Musica obj where obj.titulo = ?1
            """)
    Optional<Musica> buscarPorTitulo(String titulo);

    Optional<Musica> findFirstByOrderByDuracaoDesc();

    //Usando native query
    @Query(nativeQuery = true, value = """
            select * from musica where duracao > ?1
            """)
    List<Musica> buscarMusicasComDuracaoMaiorQue(Integer duracao);
}
