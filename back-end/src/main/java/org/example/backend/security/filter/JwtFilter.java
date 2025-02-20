package org.example.backend.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.backend.models.User;
import org.example.backend.security.UserLoginMock;
import org.example.backend.security.security_services.UserLoginService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final UserLoginService userLoginService;

    //TODO De modificat roluri, folosind premium/non-premium
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");

        if (null != bearerToken && bearerToken.startsWith("Bearer ")) {
            User user = userLoginService.validateUser(bearerToken);

            if (null != user) {
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(
                                new UsernamePasswordAuthenticationToken(user.getEmail(), null, parseAuthorities(user.getRoles()))
                        );
            }
        }
        filterChain.doFilter(request, response);
    }

    private List<GrantedAuthority> parseAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
