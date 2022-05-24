package com.example.demo.DTO;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ToString
public class AddressDTO extends RepresentationModel<AddressDTO> {
     @Getter
     String street;
     @Getter
     String city;
     @Getter
     String postalCode;
     @Getter
     String countryCode;
}
