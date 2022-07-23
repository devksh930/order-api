package me.devksh930.orderapi.account.domain.repository;

import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.account.domain.Email;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    boolean existsByEmail(Email email);

    Optional<Account> findByEmailEmail(String email);
}
