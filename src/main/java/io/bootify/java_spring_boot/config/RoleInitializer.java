package io.bootify.java_spring_boot.config;

import io.bootify.java_spring_boot.entity.Role;
import io.bootify.java_spring_boot.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class RoleInitializer {

    @Bean
    @Transactional
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            addRoleIfNotFound("ROLE_ADMIN", roleRepository);
            addRoleIfNotFound("ROLE_MODERATOR", roleRepository);
            addRoleIfNotFound("ROLE_USER", roleRepository);
        };
    }

    private void addRoleIfNotFound(String roleName, RoleRepository roleRepository) {
        if (!roleRepository.existsByName(roleName)) {
            Role role = new Role(roleName);
            roleRepository.save(role);
        }
    }
}