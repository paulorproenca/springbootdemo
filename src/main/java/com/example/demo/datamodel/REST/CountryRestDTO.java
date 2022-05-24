package com.example.demo.datamodel.REST;

import lombok.Getter;

public class CountryRestDTO {

	@Getter
	private String code;
	@Getter
	private String name;

	protected CountryRestDTO() {}

	public CountryRestDTO(String code, String name) {
		this.code = code;
		this.name = name;
	}
}