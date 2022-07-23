package me.devksh930.orderapi.auth.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import me.devksh930.orderapi.account.domain.repository.AccountRepository;
import me.devksh930.orderapi.account.service.AccountAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtTokenParser {
    private final JwtParser jwtParser;
    private final AccountRepository accountRepository;

    public JwtTokenParser(@Value("${app.jwt.secretKey}") String secretKey, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public Authentication extractAuthentication(final String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            List<SimpleGrantedAuthority> role = List.of(new SimpleGrantedAuthority(claims.get("ROLE").toString()));
            UserDetails userDetails = new AccountAdapter(accountRepository.findById(Long.valueOf(claims.getSubject()))
                    .orElseThrow(() -> new BadCredentialsException("")));

            return new UsernamePasswordAuthenticationToken(userDetails, token, role);
        } catch (JwtException | IllegalArgumentException | NullPointerException exception) {
            throw new BadCredentialsException(exception.getMessage());
        }
    }

    public boolean validateToken(String token) {
        try {
            log.info(token);
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException exception) {
            log.info(exception.toString());
            return false;
        }
    }
}
