package io.bootify.java_spring_boot.repository;

import io.bootify.java_spring_boot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    boolean existsByName(String name);
}
