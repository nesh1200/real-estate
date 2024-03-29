package com.example.quatro_to.repository;

import com.example.quatro_to.model.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood,Long> {

    Optional<Neighborhood> findByName(String name);
}
