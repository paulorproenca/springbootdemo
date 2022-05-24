package com.example.demo.datamodel.JPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "addresses")
public class AddressJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String city;
    private String postalCode;

	//@Embedded
    //private Country country;
    // or
    //@ManyToOne()
    //@JoinColumn(name = "country_code", nullable=false)
    //because it is in a another aggregate, a foreign key constraint is not used
    private String countryCode; // this should be of type e.g. CountryId

    @ManyToOne()
    @JoinColumn(name = "person", nullable=false)
    //because it is in the same aggregate, a foreign key constraint is used
    private PersonJpa person;

    public AddressJpa(String street, String city, String postalCode, String countryCode, PersonJpa personJpa) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.countryCode = countryCode;

        this.person = personJpa;
    }
}
