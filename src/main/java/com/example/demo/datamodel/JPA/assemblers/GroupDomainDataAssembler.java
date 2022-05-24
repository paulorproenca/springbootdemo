package com.example.demo.datamodel.JPA.assemblers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.datamodel.JPA.GroupJpa;
import com.example.demo.domain.entities.Group;
import com.example.demo.domain.valueobjects.PersonId;

@Service
public class GroupDomainDataAssembler {

	public GroupJpa toData( Group group ) {
		GroupJpa groupJpa = new GroupJpa( group.getId(), group.getName() );

		return groupJpa;
	}

	public Group toDomain( GroupJpa groupJpa ) {
		List<PersonId> adminsId = new ArrayList<PersonId>();

		Group group = new Group( groupJpa.getId(), groupJpa.getName(), adminsId );
		return group;
	}
}