package com.scm.service;

import com.scm.domain.Products;
import com.scm.domain.Role;
import com.scm.domain.User;
import com.scm.domain.enums.RoleType;
import com.scm.dto.request.ProductUpdateRequest;
import com.scm.dto.request.RegisterRequest;
import com.scm.exception.ConflictException;
import com.scm.exception.ResourceNotFoundException;
import com.scm.exception.message.ErrorMessage;
import com.scm.mapper.UserMapper;
import com.scm.repository.ProductsRepository;
import com.scm.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    private ProductsRepository productsRepository;

    private ProductsService productsService;

    public UserService(UserRepository userRepository, RoleService roleService, @Lazy PasswordEncoder passwordEncoder,
                       UserMapper userMapper, ProductsRepository productsRepository, ProductsService productsService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.productsRepository = productsRepository;
        this.productsService = productsService;
    }

    public void saveRetailer(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, registerRequest.getEmail()));
        }

//        roleService.setRole();

        Role role = roleService.findByType(RoleType.ROLE_RETAILER);

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user = new User();
        user.setName(registerRequest.getName());
        user.setPassword(encodedPassword);
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setRoles(roles);

        userRepository.save(user);
    }

    public void saveSupplier(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, registerRequest.getEmail()));
        }

        Role role = roleService.findByType(RoleType.ROLE_SUPPLIER);

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user = new User();
        user.setName(registerRequest.getName());
        user.setPassword(encodedPassword);
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setRoles(roles);

        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new
                ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, email)));
    }

    public List<Products> getAllProducts() {

        return productsRepository.findAll();
    }

    public void updateProductByUpdateProductRequest(ProductUpdateRequest productUpdateRequest) {
        List<Products> products = productsService.getAllProducts();
        try {
            for (Products product : products) {
                if (product.getProductName().equals(productUpdateRequest.getProductName())) {
                    product.setQuantity(productUpdateRequest.getQuantity());
                    productsService.saveProduct(product);
                }
            }
        } catch (ResourceNotFoundException e) {
            String.format(ErrorMessage.PRODUCT_NOT_FOUND_MESSAGE, productUpdateRequest.getProductName());
        }
    }
}
