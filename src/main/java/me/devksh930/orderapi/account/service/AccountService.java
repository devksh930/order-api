package me.devksh930.orderapi.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.account.domain.repository.AccountRepository;
import me.devksh930.orderapi.account.dto.AccountRequest;
import me.devksh930.orderapi.account.dto.AccountResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountResponse create(final AccountRequest accountRequest) {
        final Account save = accountRepository.save(accountRequest.toEntity(passwordEncoder));
        return AccountResponse.of(save);
    }


}
