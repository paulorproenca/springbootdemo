package com.example.demo.services;

import java.util.Optional;

import com.example.demo.domain.entities.Country;
import com.example.demo.domain.factories.ICountryFactory;
import com.example.demo.services.irepositories.ICountryRepository;
import com.example.demo.services.irepositories.ICountryWebRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    @Autowired ICountryFactory countryFactory;

    @Autowired ICountryRepository countryRepository;

    @Autowired ICountryWebRepository countryWebServiceRepository;

    public CountryService() {
    }

    public Country createAndSaveCountry( String code, String name ) {
        Country country = countryFactory.createCountry(code, name);
        return countryRepository.save(country);
    }

    public Optional<Country> getCountryById( String code ) {

        Optional<Country> opCountry = countryRepository.findByCode(code);

        if( opCountry.isPresent())
            return opCountry;
        else {
            return countryWebServiceRepository.findByCode(code);
        }
    }    
}
