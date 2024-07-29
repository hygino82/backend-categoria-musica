package br.dev.hygino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.hygino.entity.Gravacao;

@Repository
public interface GravacaoRepository extends JpaRepository<Gravacao, Long> {
    
   /* @Query("""
            select obj from Gravacao obj where obj.i =:id
            """)
    Optional<Gravacao> buscarGravacaoPorId(long id);*/

}
