package com.artem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController
@RequiredArgsConstructor
public class TestController {

    private UserService userService;

    @GetMapping("/test")
    public String hello() {
        String users = userService.getUsers();

        return users != null
                ? "Hello 0 users"
                : users;
    }

}