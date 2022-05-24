package com.example.demo.datamodel.JPA;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name="countries")
public class CountryJpa {
	@Id
	@Getter
	private String code;
	@Getter
	private String name;

	protected CountryJpa() {}

	public CountryJpa(String code, String name) {
		this.code = code;
		this.name = name;
	}
}