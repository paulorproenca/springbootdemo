package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.domain.entities.Country;
import com.example.demo.domain.factories.ICountryFactory;
import com.example.demo.repositories.CountryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CountryServiceTests {

	@MockBean
	CountryRepository countryRepository;

	@MockBean
	ICountryFactory countryFactory;

	@MockBean
	Country country;

	@InjectMocks
	CountryService countryService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

	@Test
	void shouldCreateACountryWithCorrectAttributes() {

		// Arrange
		when(country.getCode()).thenReturn("PT");
		when(country.getName()).thenReturn("Portugal");

		when( countryFactory.createCountry("PT", "Portugal")).thenReturn(country);

		when(countryRepository.save( country )).thenReturn(country);

		// Act
		Country country = countryService.createAndSaveCountry("PT", "Portugal");

		String code = country.getCode();
		String name = country.getName();

		// Assert
		assertEquals(code, "PT");
		assertEquals(name, "Portugal");
	}

}
