package com.example.demo.DTO;

import com.example.demo.domain.valueobjects.PersonId;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class PersonDTO extends RepresentationModel<PersonDTO> {
    @Getter
    PersonId id;
    @Getter
    String firstName;
    @Getter
    String lastName;
}
