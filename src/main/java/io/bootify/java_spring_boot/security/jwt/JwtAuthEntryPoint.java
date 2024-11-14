package io.bootify.java_spring_boot.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * AuthenticationEntryPoint is an interface provided by Spring Security, specifically for handling unauthorized access attempts.
 * How JwtAuthenticationEntryPoint Works in the Application:
 * JwtAuthenticationEntryPoint is usually referenced in the security configuration file (like SecurityConfig) to specify how unauthorized requests are handled.
 * When an unauthenticated request hits a protected endpoint, Spring Security intercepts it, triggers JwtAuthenticationEntryPoint, and the commence method executes.
 * This lets your API return a consistent 401 Unauthorized response for failed authentications, instead of allowing the request to proceed or failing silently.
 * @author Omistaja
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException
    ) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
