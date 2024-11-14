package io.bootify.java_spring_boot.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}