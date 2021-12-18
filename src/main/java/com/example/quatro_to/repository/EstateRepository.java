package com.example.quatro_to.repository;

import com.example.quatro_to.model.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateRepository extends JpaRepository<Estate,Long> {
}
