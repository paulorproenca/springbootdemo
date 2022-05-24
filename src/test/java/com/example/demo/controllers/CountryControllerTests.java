package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.Optional;

import com.example.demo.domain.entities.Country;
import com.example.demo.domain.factories.ICountryFactory;
import com.example.demo.services.CountryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class CountryControllerTests {

	@MockBean
	CountryService countryService;

	@MockBean
	ICountryFactory countryFactory;

	@MockBean
	Country country;

	@InjectMocks
	CountryController countryController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

	@Test
	void shouldCreateACountryWithCorrectAttributesAsync() {

		// Arrange
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Country countryDouble = mock(Country.class);
		when( countryDouble.getCode() ).thenReturn("PT");
		when( countryDouble.getName() ).thenReturn("Portugal");

		Optional<Country> opCountry = Optional.of(countryDouble);
		when( countryService.getCountryById("PT")).thenReturn(opCountry);

		// Act
        ResponseEntity<Country> responseEntity = countryController.getById("PT");

		// Assert
		assertEquals(responseEntity.getStatusCodeValue(), 200);

		Country countryRes = responseEntity.getBody();
		assertEquals(countryRes.getCode(), "PT");
		assertEquals(countryRes.getName(), "Portugal");
	}
}
