package me.devksh930.orderapi.auth.domain.repository;

import me.devksh930.orderapi.auth.domain.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long> {
}
