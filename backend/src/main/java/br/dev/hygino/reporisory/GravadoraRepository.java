package br.dev.hygino.reporisory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.hygino.entity.Gravadora;

@Repository
public interface GravadoraRepository extends JpaRepository<Gravadora, Long> {

}
