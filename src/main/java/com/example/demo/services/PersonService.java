package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import com.example.demo.domain.entities.Country;
import com.example.demo.domain.entities.Person;
import com.example.demo.domain.valueobjects.PersonId;
import com.example.demo.DTO.AddressDTO;
import com.example.demo.DTO.PersonDTO;
import com.example.demo.DTO.assemblers.PersonDomainDTOAssembler;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private PersonDomainDTOAssembler personAssembler;

    public PersonService() {
    }

    public PersonDTO createAndSavePerson(long id, String firstName, String lastName, String email, String username, String password ) {

        Person newPerson = new Person(id, firstName, lastName, email, username, password);

        Person newPersonSaved = personRepository.save(newPerson);

        PersonDTO personDTO = personAssembler.toDTO(newPersonSaved.getId(), newPersonSaved.getFirstName(), newPersonSaved.getLastName());
        
        return personDTO;
    }

    @Transactional
    public Optional<PersonDTO> findById(PersonId id) {
        
        Optional<Person> opPerson = personRepository.findById(id);

        if( opPerson.isPresent() ) {
            Person person = opPerson.get();
            PersonDTO personDTO = personAssembler.toDTO(person.getId(), person.getFirstName(), person.getLastName());
            return Optional.of(personDTO);
        }

        return Optional.empty();
    }

    @Transactional
    public List<PersonDTO> findAll() {
        
        List<Person> setPerson = personRepository.findAll();

        List<PersonDTO> setPersonDTO = new ArrayList<PersonDTO>();
        for(Person person : setPerson ) {
            PersonDTO personDTO = personAssembler.toDTO(person.getId(), person.getFirstName(), person.getLastName());
            setPersonDTO.add(personDTO);
        }

        return setPersonDTO;
    }

    public List<PersonDTO> findByLastName(String lastName) {
        
        List<Person> setPerson = personRepository.findByLastName(lastName);

        List<PersonDTO> setPersonDTO = new ArrayList<PersonDTO>();
        for(Person person : setPerson ) {
            PersonDTO personDTO = personAssembler.toDTO(person.getId(), person.getFirstName(), person.getLastName());
            setPersonDTO.add(personDTO);
        }

        return setPersonDTO;
    }

    public boolean addAddressToPerson( PersonId id, String street, String city, String postalCode, String countryCode ) {

        Optional<Country> opCountry = countryRepository.findByCode( countryCode );
        if( !opCountry.isPresent() ) return false; // it must throw a descriptive exception
        
        Optional<Person> opPerson = personRepository.findById( id );
        if( !opPerson.isPresent() ) return false; // it must throw a descriptive exception

        Person person = opPerson.get();
        boolean success = person.addAddress(street, city, postalCode, countryCode);
        if( success ) {
            personRepository.addAndSaveAddress(person, street, city, postalCode, countryCode);
            return true;
        }
        else
            return false; // it must throw a descriptive exception
    }

    @Transactional
    public List<AddressDTO> getAddresses(PersonId id) {
        return personRepository.findAddressesById( id );
    }
}
