package io.bootify.java_spring_boot.security;

import io.bootify.java_spring_boot.entity.Role;
import io.bootify.java_spring_boot.entity.User;
import io.bootify.java_spring_boot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
            .map((role) -> new SimpleGrantedAuthority(role.getAuthority()))
            .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
            usernameOrEmail,
            user.getPassword(),
            authorities
        );
    }

    public User addUser(User user) {
        if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())) {
            throw new IllegalArgumentException("User with this username or email already exists");
        }

        Role userRole = new Role("ROLE_USER");
        user.addRoles(userRole);

        return userRepository.save(user);
    }
}
