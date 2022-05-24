package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.domain.entities.Country;

import org.junit.jupiter.api.Test;

class CountryTests {

	@Test
	void shouldCreateACountryWithCorrectAttributes() {
		Country country = new Country("1", "PT");

		assertEquals(country.getCode(), "1");
		assertEquals(country.getName(), "PT");
	}

}
