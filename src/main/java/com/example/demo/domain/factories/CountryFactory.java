package com.example.demo.domain.factories;

import com.example.demo.domain.entities.Country;

import org.springframework.stereotype.Service;

@Service
public class CountryFactory implements ICountryFactory {

    public Country createCountry(String code, String name) {

        return (new Country( code, name));
    }

}
