package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import java.util.List;

import com.example.demo.DTO.AddressDTO;
import com.example.demo.datamodel.JPA.AddressJpa;
import com.example.demo.datamodel.JPA.PersonJpa;
import com.example.demo.datamodel.JPA.assemblers.PersonDomainDataAssembler;
import com.example.demo.domain.entities.Person;
import com.example.demo.domain.valueobjects.PersonId;
import com.example.demo.repositories.JPA.AddressJpaRepository;
import com.example.demo.repositories.JPA.PersonJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

	@Autowired
	PersonJpaRepository personJpaRepository;
	@Autowired
	AddressJpaRepository addressJpaRepository;

	@Autowired
	PersonDomainDataAssembler personAssembler;

	public Person save( Person person ) {
		PersonJpa personJpa = personAssembler.toData(person);
		
		PersonJpa savedPersonJpa = personJpaRepository.save( personJpa );
		
		return personAssembler.toDomain(savedPersonJpa);
	}

	@Transactional	
	public Optional<Person> findById(PersonId id) {
		Optional<PersonJpa> opPersonJpa = personJpaRepository.findById(id);

		if(opPersonJpa.isPresent()) {
			PersonJpa personJpa = opPersonJpa.get();
			
			Person person = personAssembler.toDomain(personJpa);
			return Optional.of(person);
		}
		else
			return Optional.empty();
	}

	@Transactional
	public Optional<Person> findByUsername(String username) {
		Optional<PersonJpa> opPersonJpa = personJpaRepository.findByUsername(username);

		if(opPersonJpa.isPresent()) {
			PersonJpa personJpa = opPersonJpa.get();
			
			Person person = personAssembler.toDomain(personJpa);
			return Optional.of(person);
		}
		else
			return Optional.empty();
	}


	@Transactional
	public List<Person> findByLastName(String lastName) {
		List<PersonJpa> setPersonJpa = personJpaRepository.findByLastName(lastName);

		List<Person> setPerson = new ArrayList<Person>();
		for( PersonJpa personJpa : setPersonJpa ) {
			Person person = personAssembler.toDomain(personJpa);
			setPerson.add(person);
		}

		return setPerson;
	}

	@Transactional
	public List<Person> findAll() {
		List<PersonJpa> setPersonJpa = personJpaRepository.findAll();

		List<Person> setPerson = new ArrayList<Person>();
		for( PersonJpa personJpa : setPersonJpa ) {
			Person person = personAssembler.toDomain(personJpa);
			setPerson.add(person);
		}

		return setPerson;
	}

	public boolean existsByUsername(String username) {
		return personJpaRepository.existsByUsername(username);
	}

	public boolean existsByEmail(String email) {
		return personJpaRepository.existsByEmail(email);
	}

	public boolean addAndSaveAddress(Person person, String street, String city, String postalCode, String countryCode) {
		PersonJpa personJpa = personAssembler.toData(person);

		personJpaRepository.save(personJpa);

		return true;
	}

	public List<AddressDTO> findAddressesById( PersonId id ) {
		
		List<AddressJpa> addressesJpa = addressJpaRepository.findAllByPersonId(id);

		List<AddressDTO> addressesDTO = new ArrayList<AddressDTO>();
		for( AddressJpa addressJpa : addressesJpa ) {
			AddressDTO addressDTO = new AddressDTO( addressJpa.getStreet(), addressJpa.getCity(), addressJpa.getPostalCode(), addressJpa.getCountryCode());

			addressesDTO.add(addressDTO);
		}

        return addressesDTO;
	}
}