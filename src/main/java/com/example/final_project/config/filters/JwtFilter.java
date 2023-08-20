package com.example.final_project.config.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.final_project.config.details.PersonDetails;
import com.example.final_project.models.Person;
import com.example.final_project.models.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.contains("Bearer ")) {
            String token = authHeader.substring("Bearer ".length());
            if (!token.isBlank()) {
                DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("SecretKey")).build().verify(token);

                Person person = Person.builder()
                        .age(Integer.parseInt(String.valueOf(decodedJWT.getClaim("age"))))
                        .email(String.valueOf(decodedJWT.getClaim("email")))
                        .name(String.valueOf(decodedJWT.getClaim("name")))
                        .phoneNumber(String.valueOf(decodedJWT.getClaim("phone")))
                        .role(Role.valueOf(decodedJWT.getClaim("role").asString()))
                        .build();

                UserDetails userDetails = new PersonDetails(person);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(token, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
