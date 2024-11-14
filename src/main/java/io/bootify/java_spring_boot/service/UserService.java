package io.bootify.java_spring_boot.service;

import io.bootify.java_spring_boot.dto.SignUpRequest;
import io.bootify.java_spring_boot.entity.User;
import io.bootify.java_spring_boot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(SignUpRequest request) {
        if (userRepository.existsByUsernameOrEmail(request.getEmail(), request.getEmail())) {
            throw new IllegalArgumentException("User with this username or email already exists");
        }

        User newUser = new User();
        newUser.setUsername(request.getEmail());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(newUser);
    }
}
