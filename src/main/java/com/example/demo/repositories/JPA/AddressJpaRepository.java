package com.example.demo.repositories.JPA;

import java.util.List;

import com.example.demo.datamodel.JPA.AddressJpa;
import com.example.demo.domain.valueobjects.PersonId;

import org.springframework.data.repository.CrudRepository;

public interface AddressJpaRepository extends CrudRepository<AddressJpa, Long> {

    AddressJpa findById(long id);
    List<AddressJpa> findAllByPersonId( PersonId id);

    List<AddressJpa> findAllByStreet( String street);
    List<AddressJpa> findAll();
}
