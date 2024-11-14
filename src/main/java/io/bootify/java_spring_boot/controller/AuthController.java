package io.bootify.java_spring_boot.controller;

import io.bootify.java_spring_boot.dto.SignInRequest;
import io.bootify.java_spring_boot.dto.SignInResponse;
import io.bootify.java_spring_boot.dto.SignUpResponse;
import io.bootify.java_spring_boot.repository.UserRepository;
import io.bootify.java_spring_boot.security.CustomUserDetailsService;
import io.bootify.java_spring_boot.security.jwt.JwtTokenProvider;
import io.bootify.java_spring_boot.service.AuthService;
import io.bootify.java_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import io.bootify.java_spring_boot.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody SignUpRequest request) {
        userService.registerUser(request);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            request.getEmail(), null, new ArrayList<>());

        String jwtToken = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new SignUpResponse(jwtToken));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(new SignInResponse(
            authService.authUser(request)
        ));
    }
}
