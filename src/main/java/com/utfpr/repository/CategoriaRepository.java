package com.utfpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.entity.Categoria;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
