package com.tuonglh.coffee.samplecode.service;

import com.tuonglh.coffee.samplecode.model.Role;
import com.tuonglh.coffee.samplecode.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoleService(RoleRepository roleRepository){
    @PostConstruct
    public List<Role> fillAll(){
        List<Role> roles = roleRepository.getAllByUserId(2L);
        return roles;
    }
}
