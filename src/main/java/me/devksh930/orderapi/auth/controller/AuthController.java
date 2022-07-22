package me.devksh930.orderapi.auth.controller;

import lombok.RequiredArgsConstructor;
import me.devksh930.orderapi.auth.dto.LoginRequest;
import me.devksh930.orderapi.auth.dto.TokenResponse;
import me.devksh930.orderapi.auth.service.LoginService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody final LoginRequest loginRequest) {
        final TokenResponse tokenResponse = loginService.login(loginRequest);

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(tokenResponse.getToken());

        return new ResponseEntity<>(tokenResponse, httpHeaders, HttpStatus.OK);
    }
}
