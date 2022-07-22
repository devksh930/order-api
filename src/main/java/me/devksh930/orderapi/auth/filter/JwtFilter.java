package me.devksh930.orderapi.auth.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.devksh930.orderapi.auth.domain.JwtTokenParser;
import me.devksh930.orderapi.auth.domain.Token;
import me.devksh930.orderapi.auth.domain.repository.TokenRepository;
import me.devksh930.orderapi.auth.service.TokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenParser jwtTokenParser;
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = getHeadersToken(request);

        if (jwtTokenParser.validateToken(token)) {
            Authentication authentication = jwtTokenParser.extractAuthentication(token);
            tokenService.tokenValidate(Long.valueOf(authentication.getName()), token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getHeadersToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken)) {
            return bearerToken.substring("Bearer".length()).trim();
        }
        return "";
    }
}
