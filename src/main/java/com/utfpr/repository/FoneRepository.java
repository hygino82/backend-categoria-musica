package com.utfpr.repository;

import com.utfpr.entity.Fone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoneRepository extends JpaRepository<Fone, Long> {

}
