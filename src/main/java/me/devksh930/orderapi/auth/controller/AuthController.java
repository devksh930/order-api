package me.devksh930.orderapi.auth.controller;

import lombok.RequiredArgsConstructor;
import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.auth.config.annotation.CurrentAccount;
import me.devksh930.orderapi.auth.dto.LoginRequest;
import me.devksh930.orderapi.auth.dto.TokenResponse;
import me.devksh930.orderapi.auth.service.LoginService;
import me.devksh930.orderapi.auth.service.TokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginService loginService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody final LoginRequest loginRequest) {
        final TokenResponse tokenResponse = loginService.login(loginRequest);

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(tokenResponse.getToken());

        return new ResponseEntity<>(tokenResponse, httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(@CurrentAccount Account account) {
        tokenService.deleteToken(account.getId());
        return ResponseEntity.noContent().build();
    }
}
