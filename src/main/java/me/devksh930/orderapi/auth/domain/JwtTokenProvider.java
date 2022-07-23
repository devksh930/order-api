package me.devksh930.orderapi.auth.domain;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final Long tokenValidityTime;
    private final Key key;

    public JwtTokenProvider(@Value("${app.jwt.secretKey}") String secretKey,
                            @Value("${app.jwt.tokenValidityTime}") Long tokenValidityTime) {
        final String secretKeyString = secretKey;
        this.tokenValidityTime = tokenValidityTime;
        this.key = Keys.hmacShaKeyFor(secretKeyString.getBytes());

    }

    public String createToken(final Authentication authentication) {
        final Date now = new Date();
        final Date validityTime = new Date(now.getTime() + tokenValidityTime);
        final String authorities =authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .signWith(key)
                .claim("ROLE", authorities)
                .setIssuedAt(now)
                .setExpiration(validityTime)
                .compact();
    }


}
