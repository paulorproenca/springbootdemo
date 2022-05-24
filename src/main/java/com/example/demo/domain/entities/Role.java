package com.example.demo.domain.entities;

import com.example.demo.domain.valueobjects.ERole;
import com.example.demo.domain.valueobjects.RoleId;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Role {
	@Getter
	private RoleId id;
	@Getter
	private ERole name;

	public Role(RoleId roleId, ERole name) {
		this.id = roleId;
		this.name = name;
    }
}