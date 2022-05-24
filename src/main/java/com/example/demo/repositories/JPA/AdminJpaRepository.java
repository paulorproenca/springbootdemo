package com.example.demo.repositories.JPA;

import java.util.List;

import com.example.demo.datamodel.JPA.AdminJpa;

import org.springframework.data.repository.CrudRepository;

public interface AdminJpaRepository extends CrudRepository<AdminJpa, Long> {

    AdminJpa findById(long id);
    List<AdminJpa> findAll();
}
