package com.example.demo.domain.factories;

import com.example.demo.domain.entities.Country;

public interface ICountryFactory {

    public Country createCountry(String code, String name);
    
}
