package com.example.quatro_to.repository;

import com.example.quatro_to.model.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood,Long> {

    Optional<Neighborhood> findByName(String name);
}
