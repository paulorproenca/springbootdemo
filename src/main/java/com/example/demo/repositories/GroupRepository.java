package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import com.example.demo.datamodel.JPA.AdminJpa;
import com.example.demo.datamodel.JPA.GroupJpa;
import com.example.demo.datamodel.JPA.assemblers.GroupDomainDataAssembler;
import com.example.demo.domain.entities.Group;
import com.example.demo.domain.valueobjects.GroupId;
import com.example.demo.domain.valueobjects.PersonId;
import com.example.demo.repositories.JPA.AdminJpaRepository;
import com.example.demo.repositories.JPA.GroupJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GroupRepository {

	@Autowired
	GroupJpaRepository groupJpaRepository;
	@Autowired
	AdminJpaRepository adminJpaRepository;

	@Autowired
	GroupDomainDataAssembler groupAssembler;

	public Group save( Group group ) {
		GroupJpa groupJpa = groupAssembler.toData(group);

		groupJpa = groupJpaRepository.save( groupJpa );

		PersonId personId = group.getAdministrators().get(0);
		addAndSaveAdmin( group, personId);
		
		return groupAssembler.toDomain(groupJpa);
	}

	public Optional<Group> findById(GroupId id) {
		Optional<GroupJpa> opGroupJpa = groupJpaRepository.findById(id);

		if(opGroupJpa.isPresent()) {
			GroupJpa groupJpa = opGroupJpa.get();
			
			Group group = groupAssembler.toDomain(groupJpa);
			return Optional.of(group);
		}
		else
			return Optional.empty();
	}

	public List<Group> findByName(String name) {
		List<GroupJpa> setGroupJpa = groupJpaRepository.findByName(name);

		List<Group> setGroup = new ArrayList<Group>();
		for( GroupJpa groupJpa : setGroupJpa ) {
			Group group = groupAssembler.toDomain(groupJpa);
			setGroup.add(group);
		}

		return setGroup;
	}

	public List<Group> findAll() {
		List<GroupJpa> setGroupJpa = groupJpaRepository.findAll();

		List<Group> setGroup = new ArrayList<Group>();
		for( GroupJpa groupJpa : setGroupJpa ) {
			Group group = groupAssembler.toDomain(groupJpa);
			setGroup.add(group);
		}

		return setGroup;
	}

	public boolean addAndSaveAdmin(Group group, PersonId adminId) {
		GroupJpa groupJpa = groupAssembler.toData(group);

		groupJpa.addAdministrator( adminId );

		groupJpaRepository.save( groupJpa );

		return true; // it does not reflect problems that may occur
	}

	public boolean removeAndSaveAdmin(Group group, PersonId adminId) {
		GroupJpa groupJpa = groupAssembler.toData(group);

		groupJpa.removeAdministrator( adminId );

		groupJpaRepository.save( groupJpa );

		return true; // it does not reflect problems that may occur
	}

	@Transactional
	public List<PersonId> findAdminsById( GroupId id ) {
		Optional<GroupJpa> opGroupJpa = groupJpaRepository.findById(id);

		if(opGroupJpa.isPresent()) {
			GroupJpa groupJpa = opGroupJpa.get();
			
			List<AdminJpa> adminsJpa = groupJpa.getAdministrators();
			List<PersonId> adminsId = new ArrayList<PersonId>();
			for( AdminJpa adminJpa : adminsJpa ) {
				adminsId.add(adminJpa.getPersonId());
			}
			return adminsId;
		}
		else
			return null; // it should throw a descriptive exception
	}
}