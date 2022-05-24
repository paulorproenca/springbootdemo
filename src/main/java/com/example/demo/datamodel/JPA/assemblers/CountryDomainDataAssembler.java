package com.example.demo.datamodel.JPA.assemblers;

import com.example.demo.datamodel.JPA.CountryJpa;
import com.example.demo.domain.entities.Country;

import org.springframework.stereotype.Service;

@Service
public class CountryDomainDataAssembler {

	public CountryJpa toData( Country country ) {
		return new CountryJpa( country.getCode(), country.getName() );
	}

	public Country toDomain( CountryJpa country) {
		return new Country( country.getCode(), country.getName() );
	}
}