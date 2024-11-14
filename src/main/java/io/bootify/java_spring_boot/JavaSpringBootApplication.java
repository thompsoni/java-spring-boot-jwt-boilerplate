package io.bootify.java_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "io.bootify.java_spring_boot.entity")
public class JavaSpringBootApplication {

    public static void main(final String[] args) {
        SpringApplication.run(JavaSpringBootApplication.class, args);
    }
}
