package com.example.demo.datamodel.REST.assemblers;

import com.example.demo.datamodel.REST.CountryRestDTO;
import com.example.demo.domain.entities.Country;

import org.springframework.stereotype.Service;

@Service
public class CountryDomainDataRestAssembler {

	public CountryRestDTO toData( Country country ) {
		return new CountryRestDTO( country.getCode(), country.getName() );
	}

	public Country toDomain( CountryRestDTO countryRestDTO) {
		return new Country( countryRestDTO.getCode(), countryRestDTO.getName() );
	}
}