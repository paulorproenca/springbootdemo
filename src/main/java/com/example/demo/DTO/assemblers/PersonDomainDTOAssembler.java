package com.example.demo.DTO.assemblers;

import com.example.demo.DTO.PersonDTO;
import com.example.demo.domain.valueobjects.PersonId;

import org.springframework.stereotype.Service;

@Service
public class PersonDomainDTOAssembler {

    private PersonDomainDTOAssembler() {
    }

    public PersonDTO toDTO(PersonId id, String firstName, String lastName ) {
        return new PersonDTO(id, firstName, lastName);
    }
}