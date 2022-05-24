package com.example.demo.DTO.assemblers;

import com.example.demo.DTO.GroupDTO;
import com.example.demo.domain.valueobjects.GroupId;

import org.springframework.stereotype.Service;

@Service
public class GroupDomainDTOAssembler {

    private GroupDomainDTOAssembler() { 
    }

    public GroupDTO toDTO(GroupId id, String name ) {
        return new GroupDTO(id, name);
    }
}