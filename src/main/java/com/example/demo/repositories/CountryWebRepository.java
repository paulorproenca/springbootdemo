package com.example.demo.repositories;

import java.util.Optional;

import com.example.demo.datamodel.REST.CountryRestDTO;
import com.example.demo.datamodel.REST.assemblers.CountryDomainDataRestAssembler;
import com.example.demo.domain.entities.Country;
import com.example.demo.repositories.REST.CountryRestRepository;
import com.example.demo.services.irepositories.ICountryWebRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CountryWebRepository implements ICountryWebRepository {

	@Autowired CountryRestRepository countryRestRepository;

	@Autowired CountryDomainDataRestAssembler countryAssembler;

	public Optional<Country> findByCode(String code) {
		Optional<CountryRestDTO> opCountry = countryRestRepository.findByCode(code);

		if ( opCountry.isPresent() ) {
			Country country = countryAssembler.toDomain( opCountry.get() );
			return Optional.of( country );
		}
		else
			return Optional.empty();
	}
}