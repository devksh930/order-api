package me.devksh930.orderapi.account.domain;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void createAccount() {
        Account account = Account.builder()
                .name("이름")
                .nickname("nickname")
                .email("test@test.com")
                .phoneNumber("01012341234")
                .password("Test1234!11")
                .passwordEncoder(passwordEncoder)
                .accountGender("NONE")
                .build();

        assertThat(account).isNotNull();
        assertThat(account.getPassword()).isNotEqualTo("Test1234!11");
    }
}