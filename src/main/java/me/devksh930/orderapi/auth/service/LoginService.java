package me.devksh930.orderapi.auth.service;

import lombok.RequiredArgsConstructor;
import me.devksh930.orderapi.auth.dto.LoginRequest;
import me.devksh930.orderapi.auth.dto.TokenResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenService tokenService;

    public TokenResponse login(final LoginRequest loginRequest) {
        final AuthenticationManager authenticationManager = authenticationManagerBuilder.getObject();
        final Authentication authentication = authenticationManager.authenticate(loginRequest.toAuthentication());

        final TokenResponse tokenResponse = tokenService.createTokenResponse(authentication);
        tokenService.saveToken(authentication.getName(), tokenResponse.getToken());

        return tokenResponse;
    }
}
