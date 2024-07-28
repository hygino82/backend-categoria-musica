package br.dev.hygino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.hygino.entity.Gravadora;

@Repository
public interface GravadoraRepository extends JpaRepository<Gravadora, Long> {

}
