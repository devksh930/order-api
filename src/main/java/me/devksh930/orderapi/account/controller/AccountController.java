package me.devksh930.orderapi.account.controller;

import lombok.RequiredArgsConstructor;
import me.devksh930.orderapi.account.dto.AccountRequest;
import me.devksh930.orderapi.account.dto.AccountResponse;
import me.devksh930.orderapi.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody final AccountRequest accountRequest) {
        AccountResponse accountResponse = accountService.create(accountRequest);
        URI uri = URI.create("/api/users/" + accountResponse.getId());
        return ResponseEntity.created(uri).body(accountResponse);
    }
}
