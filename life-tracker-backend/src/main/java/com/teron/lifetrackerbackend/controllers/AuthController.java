package com.teron.lifetrackerbackend.controllers;

import com.teron.lifetrackerbackend.entities.User;
import com.teron.lifetrackerbackend.enums.Role;
import com.teron.lifetrackerbackend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @GetMapping("/{login}")
    void login(@PathVariable String login,
               @RequestParam(name = "password", required = true) String password) {
        log.info("Login: " + login);
        log.info("Password: " + password);
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestBody User user) {
        user.setRole(Role.ROLE_USER);
        return authenticationService.signUp(user);
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestBody User user) {
        return authenticationService.signIn(user.getUsername(), user.getPassword());
    }
}
