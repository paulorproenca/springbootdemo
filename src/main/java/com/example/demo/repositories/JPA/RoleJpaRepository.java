package com.example.demo.repositories.JPA;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.datamodel.JPA.RoleJpa;
import com.example.demo.domain.valueobjects.ERole;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleJpa, Long> {
	Optional<RoleJpa> findByName(ERole name);
}