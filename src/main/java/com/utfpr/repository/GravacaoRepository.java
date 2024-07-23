package com.utfpr.repository;

import com.utfpr.entity.Gravacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GravacaoRepository extends JpaRepository<Gravacao, Long> {

}
