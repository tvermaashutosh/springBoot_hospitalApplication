package com.example.hospital.security;

import com.example.hospital.entity.UserEntity;
import com.example.hospital.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Collection;
import java.util.Enumeration;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserRepository repository;
    private final AuthenticationUtility utility;
    private final HandlerExceptionResolver handlerExceptionResolver;
//    @Autowired
//    @Qualifier("handlerExceptionResolver")
//    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) /*throws ServletException, IOException*/ {
        try {
            log.info("Slf4j HEADERS LOGGING BEGIN...");

            log.info("Request url = {}", request.getRequestURI());

            Enumeration<String> headers = request.getHeaderNames();
            while (headers.hasMoreElements()) {
                String key = headers.nextElement();
                Enumeration<String> values = request.getHeaders(key);

                while (values.hasMoreElements()) {
                    String value = values.nextElement();
                    log.info("Header: {} = {}", key, value);
                }
            }

            log.info("Slf4j HEADERS LOGGING END...");

            final String tokenHeader = request.getHeader("Authorization");
            if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            final String token = tokenHeader.split(" ")[1];
            final String username = utility.getUsernameFromToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserEntity user = repository.findByUsername(username).orElseThrow();

                log.info("Slf4j AUTHORITIES LOGGING BEGIN...");

                Collection<SimpleGrantedAuthority> authorities = user.getAuthorities();
                for (SimpleGrantedAuthority authority : authorities)
                    log.info(authority.getAuthority());

                log.info("Slf4j AUTHORITIES LOGGING END...");

                UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(upaToken);
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}
