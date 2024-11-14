package io.bootify.java_spring_boot.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

    @GetMapping("/user")
    public String index() {
        return "\"user!\"";
    }

}
