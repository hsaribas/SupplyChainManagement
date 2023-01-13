package com.scm.controller;

import com.scm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private UserService userService;
}
