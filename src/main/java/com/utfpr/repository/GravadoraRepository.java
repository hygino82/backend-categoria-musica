package com.utfpr.repository;

import com.utfpr.entity.Gravadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GravadoraRepository extends JpaRepository<Gravadora, Long> {

}
