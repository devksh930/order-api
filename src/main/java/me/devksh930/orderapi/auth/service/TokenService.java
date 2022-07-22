package me.devksh930.orderapi.auth.service;

import me.devksh930.orderapi.auth.domain.JwtTokenProvider;
import me.devksh930.orderapi.auth.domain.Token;
import me.devksh930.orderapi.auth.domain.repository.TokenRepository;
import me.devksh930.orderapi.auth.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;
    private final Long tokenValidityTime;

    public TokenService(JwtTokenProvider jwtTokenProvider,
                        TokenRepository tokenRepository,
                        @Value("${app.jwt.tokenValidityTime}") Long tokenValidityTime) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenRepository = tokenRepository;
        this.tokenValidityTime = tokenValidityTime;
    }

    public TokenResponse createTokenResponse(final Authentication authentication) {
        final String token = jwtTokenProvider.createToken(authentication);
        return new TokenResponse(token);
    }

    public void saveToken(String id, String token) {
        tokenRepository.save(new Token(Long.parseLong(id), token, tokenValidityTime));
    }
}
