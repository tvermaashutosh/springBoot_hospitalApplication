package com.example.hospitalApplication.security;

import com.example.hospitalApplication.model.UserEntity;
import com.example.hospitalApplication.repository.UserRepository;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String clazz;

    private final UserRepository repository;
    private final JwtUtil utility;
    private final HandlerExceptionResolver handlerExceptionResolver;
//    @Autowired
//    @Qualifier("handlerExceptionResolver")
//    private HandlerExceptionResolver resolver;

    static {
        clazz = new Throwable().getStackTrace()[0].getClass().getSimpleName();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) /*throws ServletException, IOException*/ {
        try {
            log.info("{} HEADERS LOGGING BEGIN", clazz);

            log.info("Request url: {}", request.getRequestURI());

            Enumeration<String> headers = request.getHeaderNames();
            while (headers.hasMoreElements()) {
                String key = headers.nextElement();
                Enumeration<String> values = request.getHeaders(key);

                while (values.hasMoreElements()) {
                    String value = values.nextElement();
                    log.info("Header: {} = {}", key, value);
                }
            }

            log.info("{} HEADERS LOGGING END", clazz);

            String tokenHeader = request.getHeader("Authorization");
            if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = tokenHeader.split(" ")[1];
            String username = utility.getUsernameFromToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserEntity user = repository.findByUsername(username).orElseThrow();

                log.info("{} AUTHORITIES LOGGING BEGIN", clazz);

                Collection<SimpleGrantedAuthority> authorities = user.getAuthorities();
                for (SimpleGrantedAuthority authority : authorities)
                    log.info(authority.getAuthority());

                log.info("{} AUTHORITIES LOGGING END", clazz);

                UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(upaToken);
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}
