package com.example.demo.repositories;

import java.util.Optional;

import com.example.demo.datamodel.JPA.CountryJpa;
import com.example.demo.datamodel.JPA.assemblers.CountryDomainDataAssembler;
import com.example.demo.domain.entities.Country;
import com.example.demo.repositories.JPA.CountryJpaRepository;
import com.example.demo.services.irepositories.ICountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CountryRepository implements ICountryRepository {

	@Autowired
	CountryJpaRepository countryJpaRepository;
	@Autowired
	CountryDomainDataAssembler countryAssembler;

	public Country save( Country country ) {
		CountryJpa countryJpa = countryAssembler.toData(country);

		CountryJpa savedCountryJpa = countryJpaRepository.save( countryJpa );

		return countryAssembler.toDomain(savedCountryJpa);
	}

	public Optional<Country> findByCode(String code) {
		Optional<CountryJpa> opCountry = countryJpaRepository.findByCode(code);

		if ( opCountry.isPresent() ) {
			Country country = countryAssembler.toDomain(opCountry.get());
			return Optional.of( country );
		}
		else
			return Optional.empty();
	}
}