package com.scm.service;

import com.scm.domain.User;
import com.scm.dto.request.RegisterRequest;
import com.scm.exception.ConflictException;
import com.scm.exception.ResourceNotFoundException;
import com.scm.exception.message.ErrorMessage;
import com.scm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    public void saveRetailer(RegisterRequest registerRequest) {
//        if (retailerRepository.existsByEmail(registerRequest.getEmail())) {
//            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, registerRequest.getEmail()));
//        }
//
//        Role role = roleService.findByType(RoleType.ROLE_RETAILER);
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//
//        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
//
//        Retailer retailer = new Retailer();
//        retailer.setName(registerRequest.getName());
//        retailer.setPassword(registerRequest.getPassword());
//        retailer.setEmail(registerRequest.getEmail());
//        retailer.setPhoneNumber(registerRequest.getPhoneNumber());
//        retailer.setRoles(roles);
//
//        retailerRepository.save(retailer);
//    }

//    public void saveSupplier(RegisterRequest registerRequest) {
//        if (supplierRepository.existsByEmail(registerRequest.getEmail())) {
//            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, registerRequest.getEmail()));
//        }
//
//        Role role = roleService.findByType(RoleType.ROLE_RETAILER);
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//
//        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
//
//        Supplier supplier = new Supplier();
//        supplier.setName(registerRequest.getName());
//        supplier.setPassword(registerRequest.getPassword());
//        supplier.setEmail(registerRequest.getEmail());
//        supplier.setPhoneNumber(registerRequest.getPhoneNumber());
//        user.setRoles(roles);
//
//        supplierRepository.save(supplier);
//    }

//    public User getUserByEmail(String email) {
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new
//                ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, email)));
//        return user;
//    }
}
