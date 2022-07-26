package me.devksh930.orderapi.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Account API", description = "회원가입, 회원정보 조회 API")
public class AccountController {

    private final AccountService accountService;

    @Operation( summary = "회원가입")
    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody final AccountRequest accountRequest) {
        AccountResponse accountResponse = accountService.create(accountRequest);
        URI uri = URI.create("/api/accounts/" + accountResponse.getId());
        return ResponseEntity.created(uri).body(accountResponse);
    }

    @Operation( summary = "내 정보 조회")
    @GetMapping
    public ResponseEntity<AccountResponse> me(@CurrentAccount Account account) {
        return ResponseEntity.ok(AccountResponse.of(account));
    }

}
