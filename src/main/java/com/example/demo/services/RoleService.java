package com.example.demo.services;

import com.example.demo.domain.entities.Role;
import com.example.demo.domain.valueobjects.ERole;
import com.example.demo.domain.valueobjects.RoleId;
import com.example.demo.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository  roleRepository;

    public RoleService() {
    }

    public Role createAndSaveRole( RoleId id, ERole name ) {
        Role role = new Role(id, name);

        return roleRepository.save(role);
    }
}