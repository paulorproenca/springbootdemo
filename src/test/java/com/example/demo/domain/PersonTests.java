package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.domain.entities.Person;
import com.example.demo.domain.valueobjects.PersonId;

import org.junit.jupiter.api.Test;

class PersonTests {

	@Test
	void shouldCreateAPersonWithCorrectAttributes() {

		Person person = new Person(1L,"Jack", "Bauer", "email_jack", "jack", "password");

        assertEquals(person.getId(), new PersonId(1L));
        assertEquals(person.getFirstName(), "Jack");
        assertEquals(person.getLastName(), "Bauer");
        assertEquals(person.getEmail(), "email_jack");
        assertEquals(person.getUsername(), "jack");
        assertEquals(person.getPassword(), "password");
    }

	@Test
	void shouldAddAddress() {

        // Arrange
        Person person = new Person(1L,"Jack", "Bauer", "email_jack", "jack", "password");

        // Act
        person.addAddress("street", "city", "1234-567", "PT");

        // Assert
        assertEquals(person.getAddresses().size(), 1);
	}

}
