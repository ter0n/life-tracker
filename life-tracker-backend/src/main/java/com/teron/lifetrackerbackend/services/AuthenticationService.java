package com.teron.lifetrackerbackend.services;

import com.teron.lifetrackerbackend.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     * @param user данные пользователя
     * @return токен
     */
    public String signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        var newUser = User.builder()
//                .username(request.getUsername())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.ROLE_USER)
//                .build();
        userService.createUser(user);
        var jwt = jwtService.generateToken(user);
        return jwt;
    }

    public String signIn(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        var user = userService.userDetailsService().loadUserByUsername(username);
        var jwt = jwtService.generateToken(user);
        return jwt;
    }


}
