package me.devksh930.orderapi.account.domain.repository;

import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.account.domain.Email;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {
    boolean existsByEmail(Email email);
}
