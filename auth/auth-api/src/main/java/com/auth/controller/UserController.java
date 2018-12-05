package com.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/userController")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/userinfo")
    public Principal user(Principal principal)
    {
        return principal;
    }

    @GetMapping("/{name}/info")
    public String getBlogById(@PathVariable String name) {
        return "hello,"+ name;
    }
}