package io.bootify.java_spring_boot.service;

import io.bootify.java_spring_boot.dto.SignInRequest;
import io.bootify.java_spring_boot.repository.UserRepository;
import io.bootify.java_spring_boot.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String authUser(SignInRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        return jwtTokenProvider.generateToken(authentication);
    }
}
