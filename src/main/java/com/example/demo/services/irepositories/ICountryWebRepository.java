package com.example.demo.services.irepositories;

import java.util.Optional;
import com.example.demo.domain.entities.Country;

public interface ICountryWebRepository {

	public Optional<Country> findByCode( String code );
}