package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import com.example.demo.domain.entities.Group;
import com.example.demo.domain.entities.Person;
import com.example.demo.domain.valueobjects.GroupId;
import com.example.demo.domain.valueobjects.PersonId;
import com.example.demo.DTO.GroupDTO;
import com.example.demo.DTO.assemblers.GroupDomainDTOAssembler;
import com.example.demo.repositories.GroupRepository;
import com.example.demo.repositories.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private GroupDomainDTOAssembler groupAssembler;

    public GroupService() {
    }

    public GroupDTO createAndSaveGroup(long id, String name, PersonId adminId ) {

        Group newGroup = new Group(id, name, adminId);

        Group newGroupSaved = groupRepository.save(newGroup);

        GroupDTO groupDTO = groupAssembler.toDTO(newGroupSaved.getId(), newGroupSaved.getName());
        
        return groupDTO;
    }

    public Optional<GroupDTO> findById(GroupId id) {
        
        Optional<Group> opGroup = groupRepository.findById(id);

        if( opGroup.isPresent() ) {
            Group group = opGroup.get();
            GroupDTO groupDTO = groupAssembler.toDTO(group.getId(), group.getName());
            return Optional.of(groupDTO);
        }

        return Optional.empty();
    }

    public List<GroupDTO> findAll() {
        
        List<Group> setGroup = groupRepository.findAll();

        List<GroupDTO> setGroupDTO = new ArrayList<GroupDTO>();
        for(Group group : setGroup ) {
            GroupDTO groupDTO = groupAssembler.toDTO(group.getId(), group.getName());
            setGroupDTO.add(groupDTO);
        }

        return setGroupDTO;
    }

    public List<GroupDTO> findByName(String name) {
        
        List<Group> setGroup = groupRepository.findByName(name);

        List<GroupDTO> setGroupDTO = new ArrayList<GroupDTO>();
        for(Group group : setGroup ) {
            GroupDTO groupDTO = groupAssembler.toDTO(group.getId(), group.getName());
            setGroupDTO.add(groupDTO);
        }

        return setGroupDTO;
    }

    @Transactional
    public boolean addAdminToGroup( GroupId groupId, PersonId adminId ) {

        Optional<Person> opPerson = personRepository.findById( adminId );
        if( !opPerson.isPresent() ) return false; // it must throw a descriptive exception

        Optional<Group> opGroup = groupRepository.findById( groupId );
        if( !opGroup.isPresent() ) return false; // it must throw a descriptive exception

        Group group = opGroup.get();
        boolean success = group.addAdministrator( adminId );
        if( success ) {
            groupRepository.addAndSaveAdmin(group, adminId );
            return true;
        }
        else
            return false; // it must throw a descriptive exception
    }

    @Transactional
    public boolean removeAdminFromGroup( GroupId groupId, PersonId adminId ) {

        Optional<Person> opPerson = personRepository.findById( adminId );
        if( !opPerson.isPresent() ) return false; // it must throw a descriptive exception

        Optional<Group> opGroup = groupRepository.findById( groupId );
        if( !opGroup.isPresent() ) return false; // it must throw a descriptive exception

        Group group = opGroup.get();
        boolean success = group.removeAdministrator( adminId );
        if( success ) {
            groupRepository.removeAndSaveAdmin(group, adminId );
            return true;
        }
        else
            return false; // it must throw a descriptive exception
    }


    public List<PersonId> getAdministrators(GroupId groupId) {
        return groupRepository.findAdminsById( groupId );
    }
}