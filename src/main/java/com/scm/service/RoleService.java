package com.scm.service;

import com.scm.domain.Role;
import com.scm.domain.enums.RoleType;
import com.scm.exception.ResourceNotFoundException;
import com.scm.exception.message.ErrorMessage;
import com.scm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByType(RoleType roleType) {
        return roleRepository.findByType(roleType).orElseThrow(() -> new
                ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, roleType.name())));
    }

    public void setRole(){
        List<Role> roles = new ArrayList<>();

        Role supplierRole = new Role();
        supplierRole.setType(RoleType.ROLE_SUPPLIER);

        Role retailerRole = new Role();
        retailerRole.setType(RoleType.ROLE_RETAILER);

        roles.add(supplierRole);
        roles.add(retailerRole);

        roleRepository.saveAll(roles);
    }
}
