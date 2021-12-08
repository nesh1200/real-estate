package com.example.quatro_to.repository;

import com.example.quatro_to.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FloorRepository extends JpaRepository<Floor,Long> {

    Optional<Floor> findByNumber(Long number);
}
