package io.bootify.java_spring_boot.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignInResponse {
    private String token;
}