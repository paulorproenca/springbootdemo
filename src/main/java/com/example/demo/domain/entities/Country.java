package com.example.demo.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Country {
	@Getter
	private String code;
	@Getter
	private String name;

	//private Country() {}

	public Country(String code, String name ) {
		this.code = code;
		this.name = name;
	}
}