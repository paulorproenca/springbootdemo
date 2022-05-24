package com.example.demo.services.irepositories;

import java.util.Optional;
import com.example.demo.domain.entities.Country;

public interface ICountryRepository {

	public Country save( Country country );
	public Optional<Country> findByCode( String code );
}