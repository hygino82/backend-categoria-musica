package com.utfpr.repository;

import com.utfpr.dto.GravacaoDTO;
import com.utfpr.entity.Gravacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GravacaoRepository extends JpaRepository<Gravacao, Long> {

    @Query("""
            select obj from Gravacao obj where obj.id = :id
            """)
    Optional<Gravacao> buscarGravacaoPorId(Long id);
}
