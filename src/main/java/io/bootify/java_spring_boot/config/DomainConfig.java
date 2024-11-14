package io.bootify.java_spring_boot.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.bootify.java_spring_boot.entity")
@EnableJpaRepositories("io.bootify.java_spring_boot.repository")
@EnableTransactionManagement
public class DomainConfig {
}
