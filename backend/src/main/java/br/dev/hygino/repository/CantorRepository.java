package br.dev.hygino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.hygino.entity.Cantor;

@Repository
public interface CantorRepository extends JpaRepository<Cantor, Long> {

}
