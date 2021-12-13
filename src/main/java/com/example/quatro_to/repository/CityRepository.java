package com.example.quatro_to.repository;

import com.example.quatro_to.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    Optional<City> findByName(String name);
}
