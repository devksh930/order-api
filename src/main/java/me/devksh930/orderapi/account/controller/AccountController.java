package me.devksh930.orderapi.account.controller;

import lombok.RequiredArgsConstructor;
import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.account.dto.AccountRequest;
import me.devksh930.orderapi.account.dto.AccountResponse;
import me.devksh930.orderapi.account.service.AccountService;
import me.devksh930.orderapi.auth.config.annotation.CurrentAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody final AccountRequest accountRequest) {
        AccountResponse accountResponse = accountService.create(accountRequest);
        URI uri = URI.create("/api/accounts/" + accountResponse.getId());
        return ResponseEntity.created(uri).body(accountResponse);
    }

    @GetMapping
    public ResponseEntity<AccountResponse> me(@CurrentAccount Account account) {
        return ResponseEntity.ok(AccountResponse.of(account));
    }

}
