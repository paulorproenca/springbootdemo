package com.example.demo.datamodel.JPA.assemblers;

import com.example.demo.datamodel.JPA.RoleJpa;
import com.example.demo.domain.entities.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleDomainDataAssembler {

	public RoleJpa toData( Role role ) {
		return new RoleJpa( role.getId(), role.getName() );
	}

	public Role toDomain( RoleJpa roleJpa) {
		return new Role( roleJpa.getId(), roleJpa.getName() );
	}
}