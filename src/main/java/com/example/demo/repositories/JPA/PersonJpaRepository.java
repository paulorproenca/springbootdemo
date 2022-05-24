package com.example.demo.repositories.JPA;

import java.util.Optional;
import java.util.List;

import com.example.demo.datamodel.JPA.PersonJpa;
import com.example.demo.domain.valueobjects.PersonId;

import org.springframework.data.repository.CrudRepository;

public interface PersonJpaRepository extends CrudRepository<PersonJpa, PersonId> {

	List<PersonJpa> findAll();

	List<PersonJpa> findByLastName(String lastName);

	Optional<PersonJpa> findById(PersonId id);

	Optional<PersonJpa> findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
}