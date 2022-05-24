package com.example.demo.controllers;

import java.util.Optional;

import com.example.demo.DTO.NewCountryInfoDTO;
import com.example.demo.domain.entities.Country;
import com.example.demo.services.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(path = "/countries")
public class CountryController {

    @Autowired
    private CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/{code}")
    @ResponseBody
    public ResponseEntity<Country> getById(@PathVariable String code) {

        Optional<Country> opCountry = service.getCountryById( code );

        if( opCountry.isPresent() ) {
            Country country = opCountry.get();
            return new ResponseEntity<>(country, HttpStatus.OK);
        }
        else
            //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
    }

    @PostMapping("")
	public ResponseEntity<Object> createCountry(@RequestBody NewCountryInfoDTO info) {

       Country country = service.createAndSaveCountry(info.getCode(), info.getName());

	   return new ResponseEntity<>(country, HttpStatus.CREATED);
    }
}

