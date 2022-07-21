package me.devksh930.orderapi.account.domain.repository;

import me.devksh930.orderapi.account.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {
}
