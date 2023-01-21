package com.scm.controller;

import com.scm.domain.Products;
import com.scm.dto.request.LoginRequest;
import com.scm.dto.request.RegisterRequest;
import com.scm.dto.response.LoginResponse;
import com.scm.dto.response.ResponseMessage;
import com.scm.dto.response.SCMResponse;
import com.scm.security.jwt.JwtUtils;
import com.scm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/retailer")
public class RetailerController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    //http://localhost:8080/retailer/register
    @PostMapping("/register")
    public ResponseEntity<SCMResponse> registerRetailer(@Valid @RequestBody RegisterRequest registerRequest) {
        userService.saveRetailer(registerRequest);

        SCMResponse response = new SCMResponse();
        response.setMessage(ResponseMessage.RETAILER_REGISTER_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //http://localhost:8080/retailer/login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateRetailer(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateJwtToken(userDetails);

        LoginResponse loginResponse = new LoginResponse(jwtToken);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    //http://localhost:8080/retailer/view/products
    @PreAuthorize("hasRole('RETAILER')")
    @GetMapping("/view/products")
    public ResponseEntity<List<Products>> viewProducts(){

        return ResponseEntity.ok(userService.getAllProducts());
    }
}
