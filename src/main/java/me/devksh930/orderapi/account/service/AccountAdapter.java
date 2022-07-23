package me.devksh930.orderapi.account.service;

import me.devksh930.orderapi.account.domain.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class AccountAdapter extends User {
    private final Account account;

    public AccountAdapter(Account account) {
        super(account.getId().toString(), account.getPassword(),
                List.of(new SimpleGrantedAuthority(account.getAccountRole().name())));
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
