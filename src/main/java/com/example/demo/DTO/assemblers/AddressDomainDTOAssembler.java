package com.example.demo.DTO.assemblers;

import com.example.demo.DTO.AddressDTO;

public class AddressDomainDTOAssembler {

    private AddressDomainDTOAssembler() {
    }

    public static AddressDTO toDTO(String street, String city, String postalCode, String countryCode ) {
        return new AddressDTO(street, city, postalCode, countryCode);
    }
}