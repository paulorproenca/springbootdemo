package com.example.demo.repositories.JPA;

import java.util.List;
import java.util.Optional;

import com.example.demo.datamodel.JPA.CountryJpa;

import org.springframework.data.repository.CrudRepository;

public interface CountryJpaRepository extends CrudRepository<CountryJpa, String> {

    Optional<CountryJpa> findByCode(String code);
    
    List<CountryJpa> findAll();
}
