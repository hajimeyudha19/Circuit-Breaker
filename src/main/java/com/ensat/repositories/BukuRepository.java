package com.ensat.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensat.entities.Buku;



public interface BukuRepository extends JpaRepository<Buku, Long>{

    List<Buku> findAllByIsActive(Boolean isActive);

    Optional<Buku>findByIdAndIsActive(Long id, Boolean isActive);
    
}
