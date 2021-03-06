package com.example.demo.domain.entities;

import java.util.List;
import java.util.ArrayList;

import com.example.demo.domain.valueobjects.GroupId;
import com.example.demo.domain.valueobjects.PersonId;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Group {
	private GroupId id;
	private String name;
	List<PersonId> administrators = new ArrayList<PersonId>();

	public Group(long id, String name, PersonId adminId ) {
		this.id = new GroupId(id);

		this.name = name;
		
		this.administrators.add( adminId );
	}

	public Group(GroupId id, String name, List<PersonId> admins ) {
		this.id = id;

		this.name = name;
		this.administrators = admins;
	}

	public boolean addAdministrator( PersonId adminId ) {
		return administrators.add( adminId );
	}

	public boolean removeAdministrator( PersonId adminId ) {
		return administrators.remove( adminId );
	}

	public List<PersonId> getAdministrators() {
		return administrators;
	}
}