package com.utfpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
    
}
